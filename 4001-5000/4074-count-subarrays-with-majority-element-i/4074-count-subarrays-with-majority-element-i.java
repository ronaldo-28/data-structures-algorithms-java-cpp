class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
               int[] count = new int[nums.length];

        if (nums[0] == target) {
            count[0] = 1;
        } else {
            count[0] = 0;
        }


        for (int i = 1; i < nums.length; i++) {

            if (nums[i] == target) {
                count[i] = count[i - 1] + 1;
            } else {
                count[i] = count[i - 1];
            }
        }

        int res = 0;

        for (int j = 0; j < nums.length; j++) {
            int size = j + 1;
            int targetSize = count[j];

            if (targetSize > size / 2) {
                res++;
            }
        }


        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int size = j - i + 1;
                int targetSize = count[j] - count[i - 1];

                if (targetSize > size / 2) {
                    res++;
                }
            }
        }
        return res;
 
    }
}