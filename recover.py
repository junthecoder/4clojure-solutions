#!/usr/bin/env python3

import json
import re
from dataclasses import dataclass
from datetime import datetime, timedelta, timezone
from pathlib import Path

# The anonymized archive user ID associated with lambda4fun's submissions.
USER_ID = "583e27e2e4b089d5ab817daa"

# Resolve archive inputs and recovered output relative to the working directory.
ARCHIVE_DIR = Path(".")
PROBLEMS_FILE = ARCHIVE_DIR / "problems.json"
SOLUTIONS_FILE = ARCHIVE_DIR / "original-archive" / "4clojure-solutions.json"
OUTPUT_DIR = ARCHIVE_DIR / "solutions"

JST = timezone(timedelta(hours=9))


@dataclass(frozen=True)
class Solution:
    problem_id: int
    code: str
    solution_id: str
    solved_at: datetime


def normalize_problem_id(value: object) -> int:
    # The dump may use plain JSON values or MongoDB Extended JSON numeric wrappers.
    if isinstance(value, dict):
        for key in ("$numberLong", "$numberInt"):
            if key in value:
                value = value[key]
                break

    # bool is an int subclass, but is not a valid problem ID.
    if isinstance(value, int) and not isinstance(value, bool):
        return value

    if isinstance(value, str):
        return int(value)

    raise ValueError(f"Unknown problem ID format: {value!r}")


def objectid_datetime(oid: object) -> datetime:
    """Decode the creation timestamp embedded in a MongoDB ObjectId."""
    if not isinstance(oid, str) or not re.fullmatch(r"[0-9a-fA-F]{24}", oid):
        raise ValueError(f"Invalid MongoDB ObjectId: {oid!r}")

    # The first four bytes (eight hex digits) encode Unix time in seconds.
    timestamp = int(oid[:8], 16)
    return datetime.fromtimestamp(timestamp, timezone.utc)


def slugify(title: str) -> str:
    slug = title.lower()
    # Drop apostrophes without splitting words, then turn other separators into hyphens.
    slug = re.sub(r"['’]", "", slug)
    slug = re.sub(r"[^a-z0-9]+", "-", slug)
    return slug.strip("-") or "problem"


def load_problem_metadata(path: Path = PROBLEMS_FILE) -> dict[int, dict]:
    with path.open(encoding="utf-8") as f:
        problems = json.load(f)

    # Use integer keys so metadata matches solution IDs regardless of dump format.
    return {normalize_problem_id(problem["_id"]): problem for problem in problems}


def recover_solutions(
    path: Path = SOLUTIONS_FILE, user_id: str = USER_ID
) -> list[Solution]:
    recovered: dict[int, Solution] = {}

    # The original dump is JSON Lines: one JSON object per line.
    with path.open(encoding="utf-8") as f:
        for line_number, line in enumerate(f, start=1):
            if not line.strip():
                continue

            try:
                row = json.loads(line)
                if not isinstance(row, dict):
                    raise TypeError("Expected a JSON object")

                # Ignore other users before inspecting their solution fields.
                user = row.get("user")
                if not isinstance(user, dict) or user.get("$oid") != user_id:
                    continue

                solution_id = row["_id"]["$oid"]
                # The creation date does not reflect later edits or resubmissions.
                solved_at = objectid_datetime(solution_id).astimezone(JST)
                problem_id = normalize_problem_id(row["problem"])
                code = row["code"]
                if not isinstance(code, str):
                    raise TypeError("Solution code must be a string")

                # Each problem has one output path; duplicates would overwrite code.
                if problem_id in recovered:
                    raise ValueError(f"Duplicate solution for problem {problem_id}")

                recovered[problem_id] = Solution(
                    problem_id, code, solution_id, solved_at
                )
            except (KeyError, TypeError, ValueError) as exc:
                raise ValueError(f"{path}:{line_number}: {exc}") from exc

    # Emit solutions in problem order rather than the archive's record order.
    return [recovered[problem_id] for problem_id in sorted(recovered)]


def format_solution(
    solution: Solution, title: str, difficulty: str | None = None
) -> str:
    header = f";; 4Clojure #{solution.problem_id} — {title}"
    if difficulty:
        header += f" [{difficulty}]"

    header += f"\n;; Solved: {solution.solved_at:%Y-%m-%d}"

    # Preserve the submitted code except for trailing whitespace; end with one newline.
    return f"{header}\n\n{solution.code.rstrip()}\n"


def write_solution(
    solution: Solution,
    metadata: dict[int, dict],
    output_dir: Path = OUTPUT_DIR,
) -> Path:
    # A missing metadata entry should still produce a file with a fallback title.
    problem = metadata.get(solution.problem_id, {})
    title = problem.get("title", f"Problem {solution.problem_id}")
    filename = f"{solution.problem_id:03d}-{slugify(title)}.clj"
    output_path = output_dir / filename
    contents = format_solution(solution, title, problem.get("difficulty"))
    output_path.write_text(contents, encoding="utf-8")
    return output_path


def main() -> None:
    if not PROBLEMS_FILE.is_file():
        raise SystemExit(f"Cannot find {PROBLEMS_FILE}")

    if not SOLUTIONS_FILE.is_file():
        raise SystemExit(
            f"Cannot find {SOLUTIONS_FILE}\n"
            "Extract 4clojure-solutions.tgz into original-archive first."
        )

    metadata = load_problem_metadata()
    solutions = recover_solutions()

    if not solutions:
        raise SystemExit(f"No solutions found for user {USER_ID}")

    # Parse and validate the archive before creating or overwriting output files.
    OUTPUT_DIR.mkdir(parents=True, exist_ok=True)
    for solution in solutions:
        path = write_solution(solution, metadata)
        print(f"{path} ({solution.solved_at:%Y-%m-%d})")

    # Problem order need not match submission order, so compute date bounds separately.
    first = min(s.solved_at for s in solutions)
    last = max(s.solved_at for s in solutions)

    print()
    print(f"Recovered {len(solutions)} solutions.")
    print(f"First solution: {first:%Y-%m-%d}")
    print(f"Last solution:  {last:%Y-%m-%d}")
    print(f"Output: {OUTPUT_DIR.resolve()}")


if __name__ == "__main__":
    main()
