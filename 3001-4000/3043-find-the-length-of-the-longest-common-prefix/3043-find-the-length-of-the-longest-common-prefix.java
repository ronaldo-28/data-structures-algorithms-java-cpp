class Solution {
    private class TrieNode {
        private final TrieNode[] children;

        private TrieNode() {
            children = new TrieNode[10];
        }
    }

    private TrieNode root;
    private final int[] arr = {100000000, 10000000, 1000000, 100000, 10000, 1000, 100, 10, 1};
    
    private void add(int x) {
        TrieNode node = root;
        for (int i = count(x); i < 9; i++) {
            if (node.children[x / arr[i]] == null) {
                node.children[x / arr[i]] = new TrieNode();
            }
            node = node.children[x / arr[i]];
            x %= arr[i];
        }
    }

    private int find(int x) {
        TrieNode node = root;
        int ans = 0;
        for (int i = count(x); i < 9; i++) {
            if (node.children[x / arr[i]] == null) {
                break;
            }
            node = node.children[x / arr[i]];
            x %= arr[i];
            ans++;
        }
        return ans;
    }
    
    private int count(int x) {
        for (int i = 0; i < 9; i++) {
            if (x >= arr[i]) {
                return i;
            }
        }
        return -1;
    }

    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        root = new TrieNode();
        for (int i : arr1) {
            add(i);
        }
        int ans = 0;
        for (int i : arr2) {
            ans = Math.max(ans, find(i));
        }
        return ans;
    }
}