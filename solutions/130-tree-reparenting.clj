;; 4Clojure #130 — Tree reparenting [Hard]
;; Solved: 2017-01-08

(fn [target root]
  (let [value first
        branch? next
        children rest
        find-node (fn [tree p]
                    (->> (tree-seq branch? children tree)
                         (filter #(p %))
                         first))
        find-node-by-value (fn [tree x]
                             (find-node tree (fn [node] (= x (value node)))))
        find-parent-node (fn [tree child-node]
                           (find-node tree
                                      (fn [node]
                                        (some #{child-node} (children node)))))
        target-node (find-node-by-value root target)
        parent-node (find-parent-node root target-node)
        parent-of-parent-node (find-parent-node root parent-node)
        parent-of-parent-node (remove #{parent-node} parent-of-parent-node)
        parent-node (remove #{target-node} parent-node)]
    (if (empty? parent-node)
      root
      (if (empty? parent-of-parent-node)
        (concat target-node
                [parent-node])
        (concat target-node
                [(concat parent-node
                         [parent-of-parent-node])])))))
