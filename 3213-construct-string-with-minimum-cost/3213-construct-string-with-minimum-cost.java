class Solution {
    private static class Node {
        private final Node[] child = new Node[26];
        private boolean word;
        private int cost = Integer.MAX_VALUE;

        private Node suffix;
        private Node wordSuffix;

        private int depth;
    }

    public int minimumCost(String target, String[] words, int[] costs) {
        Node root = new Node();
        for (int i = 0; i < words.length; i++) {
            this.insert(root, words[i], costs[i]);
        }
        this.ahoCorasick(root);

        return this.findMinCost(target, root);
    }

    private void insert(Node root, String word, int cost) {
        for (int i = 0; i < word.length(); i++) {
            int letter = word.charAt(i) - 'a';

            if (root.child[letter] == null) {
                root.child[letter] = new Node();
                root.child[letter].depth = i + 1;
            }
            root = root.child[letter];
        }
        root.word = true;
        root.cost = Math.min(cost, root.cost);
    }

    private void ahoCorasick(Node root) {
        Queue<Node> q = new LinkedList<>();
        root.suffix = root;

        for (Node child : root.child) {
            if (child != null) {
                child.suffix = root;
                q.add(child);
            }
        }

        while (!q.isEmpty()) {
            Node current = q.poll();

            for (int i = 0; i < 26; i++) {
                Node child = current.child[i];

                if (child != null) {
                    Node suffix = current.suffix;
                    while (suffix != root && suffix.child[i] == null) {
                        suffix = suffix.suffix;
                    }
                    if (suffix.child[i] != null) {
                        suffix = suffix.child[i];
                    }

                    child.suffix = suffix;
                    child.wordSuffix = suffix.word
                            ? suffix
                            : suffix.wordSuffix;

                    q.add(child);
                }
            }
        }
    }

    private int findMinCost(String target, Node root) {
        int[] dp = new int[target.length() + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        Node current = root;

        for (int i = 1; i < dp.length; i++) {
            int letter = target.charAt(i - 1) - 'a';

            while (current != root && current.child[letter] == null) {
                current = current.suffix;
            }

            current = current.child[letter];
            if (current == null) {
                return -1;
            }

            Node temp = current.word ? current : current.wordSuffix;
            while (temp != null) {
                if (dp[i - temp.depth] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - temp.depth] + temp.cost);
                }
                temp = temp.wordSuffix;
            }

//            if (current.word && dp[i - current.depth] != Integer.MAX_VALUE) {
//                dp[i] = dp[i - current.depth] + current.cost;
//            }
//
//            Node wordSuffix = current.wordSuffix;
//            while (wordSuffix != null) {
//                if (dp[i - wordSuffix.depth] != Integer.MAX_VALUE) {
//                    dp[i] = Math.min(dp[i], dp[i - wordSuffix.depth] + wordSuffix.cost);
//                }
//                wordSuffix = wordSuffix.wordSuffix;
//            }
        }

        return dp[target.length()] == Integer.MAX_VALUE ? -1 : dp[target.length()];
    }
}