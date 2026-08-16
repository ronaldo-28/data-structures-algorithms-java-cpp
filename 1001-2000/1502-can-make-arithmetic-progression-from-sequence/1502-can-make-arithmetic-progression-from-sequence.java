class Solution {
    public boolean canMakeArithmeticProgression(int[] arr) {
      int n = arr.length;
        if (n <= 1) return true; 

        int minVal = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;
        for (int num : arr) {
            if (num < minVal) minVal = num;
            if (num > maxVal) maxVal = num;
        }

        if ((maxVal - minVal) % (n - 1) != 0) return false;
        int d = (maxVal - minVal) / (n - 1);
        if (d == 0) return true;

        int i = 0;
        while (i < n) {
            if (arr[i] == minVal + i * d) {
                i++;
            } else {
                int diff = arr[i] - minVal;
                if (diff % d != 0) return false;
                int pos = diff / d;

                if (pos < i || arr[pos] == arr[i]) return false;

                int temp = arr[i];
                arr[i] = arr[pos];
                arr[pos] = temp;
            }
        }
        return true;  
    }
}