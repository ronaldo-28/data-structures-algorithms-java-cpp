class Solution {
    public boolean areSentencesSimilarTwo(
            String[] sentence1,
            String[] sentence2,
            List<List<String>> similarPairs
    ) {
        if (sentence1.length != sentence2.length) {
            return false;
        }

        Map<String, Integer> wordToId = new HashMap<>(similarPairs.size() * 4 + 1);
        UnionFind uf = new UnionFind(similarPairs.size() * 2);

        int id = 0;

        for (List<String> pair : similarPairs) {
            String w1 = pair.get(0);
            String w2 = pair.get(1);

            Integer id1 = wordToId.get(w1);
            if (id1 == null) {
                id1 = id;
                wordToId.put(w1, id++);
            }

            Integer id2 = wordToId.get(w2);
            if (id2 == null) {
                id2 = id;
                wordToId.put(w2, id++);
            }

            uf.union(id1, id2);
        }

        for (int i = 0; i < sentence1.length; i++) {
            String w1 = sentence1[i];
            String w2 = sentence2[i];

            if (w1.equals(w2)) {
                continue;
            }

            Integer id1 = wordToId.get(w1);
            Integer id2 = wordToId.get(w2);

            if (id1 == null || id2 == null) {
                return false;
            }

            if (uf.find(id1) != uf.find(id2)) {
                return false;
            }
        }

        return true;
    }
}

class UnionFind {
    private final int[] parent;
    private final int[] size;

    UnionFind(int n) {
        parent = new int[n];
        size = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int find(int node) {
        while (parent[node] != node) {
            parent[node] = parent[parent[node]];
            node = parent[node];
        }

        return node;
    }

    public void union(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);

        if (root1 == root2) {
            return;
        }

        if (size[root1] < size[root2]) {
            parent[root1] = root2;
            size[root2] += size[root1];
        } else {
            parent[root2] = root1;
            size[root1] += size[root2];
        }
    }
}