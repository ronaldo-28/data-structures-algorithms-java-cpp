class Solution {
    public int findMinMoves(int[] machines) {
        int moves = 0;
        int n = machines.length;
        int sum = 0;
        for (int machine : machines) {
            sum += machine;
        }
        
        int average = sum / n;
        
        if (sum % n != 0) {
            return -1;
        }

        int cummulativeDiff = 0;
        int result = 0;

        for (int machine : machines) {
            int diff = machine - average;
            cummulativeDiff += diff;
            result = Math.max(result, Math.max(Math.abs(cummulativeDiff), diff));
        }

        return result;
    }
}