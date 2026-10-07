class Solution {

    public int maximumLength(int[] nums, int k) {
        HashMap<Integer, int[]> dp = new HashMap<>();
        int[] maxRemk = new int[k + 1];

        for (int i = 0; i < nums.length; i++) {

            int num = nums[i];
            if (!dp.containsKey(num)) {
                dp.put(num, new int[k + 1]);
            }

            int[] curr = dp.get(num);

            for (int remK = k; remK >= 0; remK--) {

                int same = curr[remK] + 1;
                int different = 0;

                if (remK > 0) {
                    different = maxRemk[remK - 1] + 1;
                }

                curr[remK] = Math.max(same, different);

                maxRemk[remK] = Math.max(maxRemk[remK], curr[remK]);
            }
        }

        return maxRemk[k];
    }
}