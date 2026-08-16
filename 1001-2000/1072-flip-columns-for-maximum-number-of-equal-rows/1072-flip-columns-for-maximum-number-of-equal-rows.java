class Solution {
    private TrieNode root = new TrieNode();
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        int max = 0;
        for(int[] row : matrix) max = Math.max(max, insert(row, row[0]));
        return max;
    }
    private int insert(int[] row, int xor) {
        TrieNode current = root;
        for(int x : row) {
            if((x ^ xor) == 0) {
                if(current.left == null) current.left = new TrieNode();
                current = current.left;
            }else {
                if(current.right == null) current.right = new TrieNode();
                current = current.right;
            }
        }
        return ++current.count;
    }
    private static final class TrieNode {
        private TrieNode left, right;
        private int count = 0;
    }
}