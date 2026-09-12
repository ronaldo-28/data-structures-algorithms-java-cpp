class Solution {
    private int[] sorted, nums;
    private int n;
    public int minThreshold(int[] nums, int k) {
        this.n = nums.length;
        this.sorted = new int[n];
        this.nums = nums;
        int left = 1, right = 0;
        for(int i = 0; i < n; i++) {
            sorted[i] = nums[i];
            right = Math.max(right, nums[i]);
        }
        Arrays.sort(sorted);
        if(!check(k, right)) return -1;

        while(left < right) {
            int mid = left + right >>> 1;
            if(check(k, mid)) right = mid;
            else left = mid + 1;
        }
        return left;
    }
    private boolean check(int k, int target) {
        BIT bit = new BIT(n);
        for(int num : nums) {
            int currentIndex = getRank(num, 0, n - 1), thresholdIndex = getRank(num + target, currentIndex, n - 1);
            k -= bit.query(thresholdIndex) - bit.query(currentIndex);
            if(k <= 0) return true;
            bit.update(currentIndex, 1);
        }
        return false;
    }
    private int getRank(int target, int left, int right) { 
        while(left < right) {
            int mid = left + right + 1 >>> 1;
            if(sorted[mid] > target) right = mid - 1;
            else left = mid;
        }
        return left;
    }
}
class BIT {
    private final int[] bit;
    private final int n;
    public BIT(int n) {
        this.n = n;
        this.bit = new int[n + 1];
    }
    public void update(int index, int val) {
        for(int i = index + 1; i <= n; i += i & -i) bit[i] += val;
    }
    public int query(int index) {
        int ans = -1;
        for(int i = index + 1; i > 0; i -= i & -i) ans += bit[i];
        return ans;
    }
}