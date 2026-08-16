class Solution {
    private int start = 0;
    private int end = 0;

    public String longestPalindrome(String s) {
        if (s.length() <= 1)
            return s;
        helper(s.toCharArray(), 0);
        return s.substring(start, end + 1);
    }

    public void helper(char[] arr, int i) {
        if (i >= arr.length - 1)
            return;

        int right = i;
        int left = i;

        while (right < arr.length - 1 && arr[right] == arr[right + 1]) {
            right++;
        }

        i = right;

        while (left > 0 && right < arr.length - 1 && arr[left - 1] == arr[right + 1]) {
            left--;
            right++;
        }

        if (end - start < right - left) {
            end = right;
            start = left;
        }

        helper(arr, ++i);
    }
}