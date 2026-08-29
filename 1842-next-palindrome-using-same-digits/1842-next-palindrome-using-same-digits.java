class Solution {
    public String nextPalindrome(String num) {
        if (num.length() == 1) {
            return "";
        }

        char[] chars = num.toCharArray();
        int mid = chars.length / 2 - 1;
        int pivot = findPivot(chars, mid);
        if (pivot == -1) {
            return "";
        }

        int insertAt = findInsertionIndex(chars, mid, pivot);
        if (pivot == insertAt) {
            return "";
        }

        swap(chars, pivot, insertAt);
        reverse(chars, pivot + 1, mid);

        int left = mid;
        int right = mid + 1 + chars.length % 2;
        while (right < chars.length) {
            chars[right] = chars[left];
            right += 1;
            left -= 1;
        }

        return new String(chars);
    }

    private static void reverse(char[] chars, int left, int right) {
        while (left < right) {
            swap(chars, left, right);
            left += 1;
            right -= 1;
        }
    }

    private static int findInsertionIndex(char[] chars, int right, int pivot) {
        while (right > pivot && chars[right] <= chars[pivot]) {
            right -= 1;
        }

        return right;
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    int findPivot(char[] chars, int end) {
        char prev = chars[end];
        while (end > 0 && chars[end] >= prev) {
            prev = chars[end];
            end -= 1;
        }

        return end;
    }
}