class Solution {
    static int[] pvs;

    public int getKth(int lo, int hi, int k) {
        if(null == pvs){
            pvs = new int[1001];
            for(int i = 1; i <= 1000; i++){
                pvs[i] = powerValue(i);
            }
        }
        int[] nums = new int[hi-lo+1];
        for(int i = lo; i <= hi; i++){
            nums[i-lo] = i;
        }
        return quickSelect(nums, 0, nums.length - 1, k - 1);  // k-1转换为索引
    }

    int quickSelect(int[] nums, int left, int right, int k) {
        if (left == right) return nums[left];
        
        // 选择pivot并分区
        int pivot = partition(nums, left, right);
        
        if (pivot == k) return nums[k];
        if (pivot > k) {
            return quickSelect(nums, left, pivot - 1, k);
        }
        return quickSelect(nums, pivot + 1, right, k);
    }
    
    int partition(int[] nums, int left, int right) {
        // 使用左端点作为pivot
        int pivotValue = pvs[nums[left]];
        int pivotNum = nums[left];
        int i = left + 1;
        int j = right;
        
        while (true) {
            // 从左向右找大于等于pivot的元素
            while (i <= j && (pvs[nums[i]] < pivotValue || 
                  (pvs[nums[i]] == pivotValue && nums[i] < pivotNum))) {
                i++;
            }
            // 从右向左找小于等于pivot的元素
            while (i <= j && (pvs[nums[j]] > pivotValue || 
                  (pvs[nums[j]] == pivotValue && nums[j] > pivotNum))) {
                j--;
            }
            
            if (i >= j) break;
            // 交换元素
            swap(nums, i, j);
            i++;
            j--;
        }
        // 将pivot放到正确位置
        swap(nums, left, j);
        return j;
    }
    
    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    int powerValue(long x) {
        int ans = 0;
        while (x > 1) {
            if ((x & 1) == 1) {
                x = 3 * x + 1;
            } else {
                x /= 2;
            }
            ans++;
        }
        return ans;
    }
}
class SolutionWA {
    static int pvs[];

    public int getKth(int lo, int hi, int k) {
        if(null == pvs){
            pvs = new int[1001];
            for(int i = 1; i<=1000; i++){
                pvs[i] = powerValue(i);
            }
        }
        int nums[] = new int[hi-lo+1];
        for(int i = lo; i<=hi; i++){
            nums[i-lo] = i;
        }
        return getKth(nums, 0, hi - lo, k);
    }

    int getKth(int nums[], int lo, int hi, int k) {
        int l = lo+1, r = hi;
        while(l < hi && pvs[nums[r]] > pvs[nums[lo]] || (pvs[nums[r]] == pvs[nums[lo]] && nums[r] > nums[lo] ))r--;
        while(l < hi && pvs[nums[l]] < pvs[nums[lo]] || (pvs[nums[l]] == pvs[nums[lo]] && nums[l] < nums[lo] ))l++;
        int t = nums[r];
        nums[r] = nums[lo];
        nums[lo] = t;
        if(l - lo == k)return nums[r];
        if(l - lo > k) return getKth(nums, lo, l, k);
        return getKth(nums, l, hi, k - (l - lo));
    }

    int powerValue(long x) {
        int ans = 0;
        while (x > 1) {
            if ((x & 1) == 1) {
                x = 3 * x + 1;
            } else {
                x /= 2;
            }
            ans++;
        }
        return ans;
    }
}