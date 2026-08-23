class Solution {
    private static final int[] queue = new int[10001];
    public int connectSticks(int[] sticks) {
        int n = sticks.length;
        quicksort(sticks, 0, n - 1);

        int ans = 0;
        int index = 0, read = 0, write = 0;
        for(int i = 0; i < n - 1; i++) {
            int sum = 0;

            if(index < n && (read == write || sticks[index] < queue[read])) sum += sticks[index++];
            else sum += queue[read++];
            
            if(index < n && (read == write || sticks[index] < queue[read])) sum += sticks[index++];
            else sum += queue[read++];

            ans += sum;
            queue[write++] = sum;
        }
        return ans;
    }

    private static void quicksort(int[] nums, int left, int right) {
        if(right <= left + 1) {
            if(right == left + 1 && nums[right] < nums[left]) swap(nums, left, right);
            return;
        }
        int part = partition(nums, left, right);
        quicksort(nums, left, part - 1);
        quicksort(nums, part + 1, right);
    }
    private static int partition(int[] nums, int left, int right) {
        int i = left + 1, j = right;

        swap(nums, i, left + right >>> 1);
        if(nums[left] > nums[right]) swap(nums, left, right);
        if(nums[i] > nums[right]) swap(nums, i, right);
        if(nums[left] > nums[i]) swap(nums, left, i);

        int pivot = nums[i];
        while(true) {
            do {
                i++;
            }while(nums[i] < pivot);
            do {
                j--;
            }while(nums[j] > pivot);

            if(i > j) break;
            swap(nums, i, j);
        }

        nums[left + 1] = nums[j];
        nums[j] = pivot;
        return j;
    }
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}