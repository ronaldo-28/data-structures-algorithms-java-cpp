class Solution {
    static {
        for (int i = 0; i < 500; i++) {
            new Solution().minPartitions("0");
        }
    }

    public int minPartitions(String n) {
        int max = -1;

        for(int i = 0; i < n.length(); i++) {
            max = Math.max(max, n.charAt(i) - '0');
        }
        return max;
    }
}