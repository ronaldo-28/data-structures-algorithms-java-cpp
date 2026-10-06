import java.util.Arrays;

class Solution {
    public String clearStars(String s) {
        int n = s.length();
        int[] head = new int[26];
        int[] next = new int[n];
        Arrays.fill(head, -1);
        
        boolean[] deleted = new boolean[n];
        char[] arr = s.toCharArray();
        
        for (int i = 0; i < n; i++) {
            if (arr[i] == '*') {
                deleted[i] = true;
                // Greedily find the smallest active character
                for (int j = 0; j < 26; j++) {
                    if (head[j] != -1) {
                        deleted[head[j]] = true; // Mark character as deleted
                        head[j] = next[head[j]]; // Pop stack in O(1)
                        break;
                    }
                }
            } else {
                int c = arr[i] - 'a';
                // Push current index to the character's linked-stack
                next[i] = head[c];
                head[c] = i;
            }
        }
        
        // Reconstruct string natively bypassing dynamic StringBuilders where possible
        char[] res = new char[n];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (!deleted[i]) {
                res[idx++] = arr[i];
            }
        }
        
        return new String(res, 0, idx);
    }
}