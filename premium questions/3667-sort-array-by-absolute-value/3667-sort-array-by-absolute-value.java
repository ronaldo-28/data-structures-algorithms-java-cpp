class Solution {
    public int[] sortByAbsoluteValue(int[] nums) {
        int[] count = new int[201];
        for(int i=0; i<nums.length; i++)
            count[100 + nums[i]]++;
        int[] res = new int[nums.length];
        int idx = 0;
        while(count[100] > 0)
        {
            res[idx++] = 0;
            count[100]--;
        }
        for(int i=1; i<=100; i++)
        {
            while(count[i+100] > 0)
            {
                res[idx++] = i;
                count[100 + i]--;
            }
            while(count[-i+100] > 0)
            {
                res[idx++] = -i;
                count[100 - i]--;
            }
        }
        return res;
    }
}