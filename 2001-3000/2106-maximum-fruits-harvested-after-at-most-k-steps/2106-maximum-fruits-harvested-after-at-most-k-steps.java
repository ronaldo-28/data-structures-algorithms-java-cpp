class Solution {
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int n = fruits.length;
        int left = lowerBound(fruits, startPos - k);

        int right = left;
        int sum = 0;
        for(; right < n && fruits[right][0] <= startPos; right++) {
            sum += fruits[right][1];
        }
        int ans = sum;

        for( ; right < n && fruits[right][0] <= startPos + k; right++) {
            sum += fruits[right][1];
            while((fruits[right][0] - startPos + fruits[right][0] - fruits[left][0]) > k && (startPos - fruits[left][0] + fruits[right][0] - fruits[left][0]) > k) {
                sum -= fruits[left][1];
                left++;
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    private int lowerBound(int[][] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid][0] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}