class Solution {
    public int convertArray(int[] nums) {
        // 3, 2, 8, 10
        int ans1 = minOps(nums);
        reverse(nums);
        return Math.min(ans1, minOps(nums));
    }

    private int minOps(int[] nums) {
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));

        int res = 0;
        int maxV = nums[0];
        // 0 5 4 2 3
        for(int i = 0; i < nums.length; i++) {
            if(!pq.isEmpty() && pq.peek() > nums[i]) {
                res += (pq.poll() - nums[i]);
                pq.offer(nums[i]);
            }

            pq.offer(nums[i]);
            // if (nums[i] >= maxV) {
            //     maxV = nums[i];
            // } else {
            //     res += (maxV - nums[i]);
            // }
        }

        return res;
    }

    void reverse(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while(l < r) {
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
            l++;
            r--;
        }
    }
}