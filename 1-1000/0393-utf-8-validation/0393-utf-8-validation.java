class Solution {
    public boolean validUtf8(int[] data) {
        return valid(data, 0, data.length - 1);
    }

    private boolean valid(int[] data, int start, int end) {
        if(start > end) {
            return true;
        }

        if(((data[start] >> 7) ^ 0) == 0) {
            return valid(data, start + 1, end);
        }
        if(start + 1 <= end && ((data[start] >> 5) ^ 6) == 0 && ((data[start + 1] >> 6) ^ 2) == 0) {
            return valid(data, start + 2, end);
        }
        if(start + 2 <= end && ((data[start] >> 4) ^ 14) == 0 && ((data[start + 1] >> 6) ^ 2) == 0 && ((data[start + 2] >> 6) ^ 2) == 0) {
            return valid(data, start + 3, end);
        }
        if(start + 3 <= end && ((data[start] >> 3) ^ 30) == 0 && ((data[start + 1] >> 6) ^ 2) == 0 && ((data[start + 2] >> 6) ^ 2) == 0 && ((data[start + 3] >> 6) ^ 2) == 0) {
            return valid(data, start + 4, end);
        }

        return false;
    }
}