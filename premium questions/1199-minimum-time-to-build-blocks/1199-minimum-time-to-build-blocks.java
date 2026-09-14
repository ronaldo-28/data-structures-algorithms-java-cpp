class Solution {
    private static final int[] queue = new int[1001]; //I use a static array instead of a normal Queue, runs much faster
    public int minBuildTime(int[] blocks, int split) {
        int n = blocks.length;
        quicksort(blocks); //sort the array

        int index = 0; //index of the current element in 'blocks'
        int read = 0, write = 0; //read is the front of the queue, write is the back

        //we perform n - 1 merges total, the variable 'i' here isn't used as an index or anything
        for(int i = 0; i < n - 1; i++) {
            //ignore the smallest element in the Queue or the Array
            if(index != n && (read == write || blocks[index] < queue[read])) index++;
            else read++;

            //add the next smallest element + split cost to the Queue
            if(index != n && (read == write || blocks[index] < queue[read])) queue[write++] = split + blocks[index++];
            else queue[write++] = split + queue[read++];
        }
        return read == write ? blocks[index] : queue[read]; //return the final element
    }




    //QUICKSORT TEMPLATE, YOU CAN IGNORE ALL THIS STUFF DOWN HERE
    private static void quicksort(int[] nums) {
        quicksort(nums, 0, nums.length - 1);
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