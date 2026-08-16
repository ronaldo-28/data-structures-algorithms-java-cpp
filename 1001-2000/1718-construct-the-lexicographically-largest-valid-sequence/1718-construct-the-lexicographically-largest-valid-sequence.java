import java.util.Arrays;

class Solution {
    int[] res;
    boolean[] used;

    public int[] constructDistancedSequence(int n) {
        int len = 2 * n - 1;
        res = new int[len];
        Arrays.fill(res, -1);
        used = new boolean[n + 1];  // used[i] indicates if i has been placed completely
        
        backtrack(0, n);
        return res;
    }
    
    private boolean backtrack(int pos, int n) {
        // If we reached the end, a valid sequence is constructed
        if (pos == res.length) {
            return true;
        }
        
        // If current position is already filled, move to the next
        if (res[pos] != -1) {
            return backtrack(pos + 1, n);
        }
        
        // Try placing numbers from n down to 1 for lexicographically largest order
        for (int i = n; i >= 1; i--) {
            // Skip if this number is already used
            if (used[i]) continue;
            
            // For number 1, which occurs only once
            if (i == 1) {
                res[pos] = 1;
                used[1] = true;
                if (backtrack(pos + 1, n)) {
                    return true;
                }
                // Backtrack
                res[pos] = -1;
                used[1] = false;
            } else {
                // For numbers greater than 1, check if the corresponding position is within bounds and empty
                if (pos + i >= res.length || res[pos + i] != -1) continue;
                
                // Place the number in both positions
                res[pos] = i;
                res[pos + i] = i;
                used[i] = true;
                
                if (backtrack(pos + 1, n)) {
                    return true;
                }
                // Backtrack if placing i did not lead to a solution
                res[pos] = -1;
                res[pos + i] = -1;
                used[i] = false;
            }
        }
        return false;
    }
}
