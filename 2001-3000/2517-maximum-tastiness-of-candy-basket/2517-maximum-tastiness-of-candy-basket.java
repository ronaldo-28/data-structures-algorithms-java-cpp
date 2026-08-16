class Solution {
    public int maximumTastiness(int[] price, int k) {
        Arrays.sort(price);
        int n = price.length;
        int left = 0, right = (price[n - 1] - price[0]) / (k - 1);
        while(left < right) {
            int mid = (right + left + 1) >> 1;
            if(check(price, mid, k)) left = mid;
            else right = mid - 1;
        }
        return right;
    }
    private boolean check(int[] price, int target, int k) {
        int prev = 0;
        for(int x : price) {
            if(x < prev) continue;
            if(--k == 0) return true;
            prev = x + target;
        }
        return false;
    }
}