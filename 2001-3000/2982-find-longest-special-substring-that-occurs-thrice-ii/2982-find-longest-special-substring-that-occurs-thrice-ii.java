class Solution {
    public int maximumLength(String s) {
        int max = -1;
        int curr_len = 0;
        char prev = '*';
        int[][] arr = new int[26][3];
        int i = 0;        
        while (i < s.length()) {
            char curr = s.charAt(i);
            if (curr == prev) {
                curr_len++;
            } else {
                curr_len = 1;
                prev = curr;
            }
            int idx = curr - 'a';
            if (curr_len > arr[idx][0]) {
                arr[idx][2] = arr[idx][1];
                arr[idx][1] = arr[idx][0];
                arr[idx][0] = curr_len;
            } else if (curr_len > arr[idx][1]) {
                arr[idx][2] = arr[idx][1];
                arr[idx][1] = curr_len;
            } else if (curr_len > arr[idx][2]) {
                arr[idx][2] = curr_len;
            }
            i++;
        }
        for (int j = 0; j < 26; j++) {
            if (arr[j][2] > 0) {
                max = Math.max(max, arr[j][2]);
            }
        }
        return max;
    }
}