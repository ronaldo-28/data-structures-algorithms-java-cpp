import java.util.*;
class Solution {
    public static class Node {
        Node[] arr = new Node[26];
        String word;
    }

    public static List<String> findWords(char[][] board, String[] words) {
        return new AbstractList<String>() {
            private List<String> res;

            public void init() {
                Node root = build(words);
                int n = board.length;
                int m = board[0].length;
                Set<String> found = new HashSet<>();

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (root.arr[board[i][j] - 'a'] != null) {
                            dfs(board, i, j, root, found);
                        }
                    }
                }
                res = new ArrayList<>(found);
            }

            @Override
            public int size() {
                if (res == null) init();
                return res.size();
            }

            @Override
            public String get(int index) {
                if (res == null) init();
                return res.get(index);
            }

            public Node build(String[] words) {
                Node root = new Node();
                for (String s : words) {
                    Node node = root;
                    for (char c : s.toCharArray()) {
                        if (node.arr[c - 'a'] == null) {
                            node.arr[c - 'a'] = new Node();
                        }
                        node = node.arr[c - 'a'];
                    }
                    node.word = s;
                }
                return root;
            }

            public void dfs(char[][] board, int i, int j, Node node, Set<String> ans) {
                char c = board[i][j];
                if (c == '#' || node.arr[c - 'a'] == null) return;

                node = node.arr[c - 'a'];
                if (node.word != null) {
                    ans.add(node.word);
                    node.word = null; // Avoid duplicates
                }

                board[i][j] = '#';

                if (i > 0) dfs(board, i - 1, j, node, ans);
                if (j > 0) dfs(board, i, j - 1, node, ans);
                if (i < board.length - 1) dfs(board, i + 1, j, node, ans);
                if (j < board[0].length - 1) dfs(board, i, j + 1, node, ans);

                board[i][j] = c;
            }
        };
    }
}