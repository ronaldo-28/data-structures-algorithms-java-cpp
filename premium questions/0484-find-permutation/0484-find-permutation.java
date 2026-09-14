class Solution {
    public int[] findPermutation(String s) {
        int n = s.length() + 1;
        int[] perm = new int[n];
        
        // 1. Initialize array with 1 to n in perfect ascending order
        for (int i = 0; i < n; i++) {
            perm[i] = i + 1;
        }
        
        // 2. Scan string and find sections of 'D' to reverse
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == 'D') {
                int start = i;
                
                // Track how far the contiguous sequence of 'D' extends
                while (i < s.length() && s.charAt(i) == 'D') {
                    i++;
                }
                
                // Reverse the elements spanning across these 'D' segments
                reverse(perm, start, i);
            } else {
                i++;
            }
        }
        
        return perm;
    }
    
    private void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
}