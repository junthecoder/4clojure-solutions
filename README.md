# 4Clojure Solutions

My solutions to the original [4Clojure](https://www.4clojure.com/) problem set.

I used 4Clojure under the username `lambda4fun`. After the original service shut down, I recovered my submitted solutions from the publicly released 4Clojure solutions archive.

The recovered solutions are stored individually under [`solutions/`](./solutions).

## Repository Structure

```text
.
├── README.md
├── LICENSE
├── deps.edn
├── recover.py
└── solutions/
    ├── 001-...
    ├── 002-...
    ├── ...
    ├── 125-gus-quinundrum.clj
    ├── 158-decurry.clj
    └── ...
```

Each solution file contains the original submitted expression together with the problem metadata:

```clojure
;; 4Clojure #158 — Decurry [Medium]
;; Solved: YYYY-MM-DD

(fn [f]
  (fn [& xs]
    (reduce #(%1 %2) f xs)))
```

## Recovery

The original 4Clojure solutions archive contains anonymized MongoDB records.

My submissions are associated with the user ID:

```text
583e27e2e4b089d5ab817daa
```

`recover.py` extracts all solution records belonging to that user and writes them as individual Clojure source files.

The original MongoDB solution documents also contain an ObjectId for each solution. Since a MongoDB ObjectId embeds its creation timestamp, the script uses it to recover the date on which each solution record was first created.

Therefore, the `Solved` date should be interpreted as the **first recorded submission date for that problem**. If a solution was edited or resubmitted later, the ObjectId timestamp would remain unchanged.

## Recovery Steps

Clone the public solutions archive:

```bash
git clone https://github.com/oxalorg/4clojure-solutions-archive.git
cd 4clojure-solutions-archive
```

Extract the original MongoDB solutions dump:

```bash
mkdir -p original-archive
tar xzf 4clojure-solutions.tgz -C original-archive
```

Place `recover.py` in the repository root, then run:

```bash
python3 recover.py
```

The script filters the original solution records by my anonymized user ID and writes the recovered submissions as individual files under:

```text
solutions/
```

## Source Archive

The data was recovered from:

- [`oxalorg/4clojure-solutions-archive`](https://github.com/oxalorg/4clojure-solutions-archive)

That repository contains an archive of the original 4Clojure problems and submitted solutions made available after the original service was discontinued.

## Notes

The solution files intentionally preserve the original submitted code rather than rewriting or modernizing it.

They represent my Clojure practice at the time and are kept primarily as an archive of my work on 4Clojure.

## License

My solution code in this repository is licensed under the MIT License.

4Clojure problem names, descriptions, and other upstream material remain the property of their respective authors and are not relicensed by this repository.
