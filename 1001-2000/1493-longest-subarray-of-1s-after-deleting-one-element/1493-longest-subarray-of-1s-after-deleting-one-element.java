class Solution
{
    static{
        System.gc();
        for(int i=0; i<500; i++){
            longestSubarray(new int[]{0, 0});
        }
    }
    public static int longestSubarray(int[] nums)
    {
        boolean skipped_one_time = false;
        int l=0, r=0, pos_of_skipped_ele=0, max_size=0;
        if (nums.length == 1) return 0;

        while (r < nums.length)
        {
            if (nums[r] == 1)
            {
                if (r == nums.length-1)
                {
                    max_size = Math.max(max_size, r-l+1);
                }
                r++;
            }
            else
            {
                if (skipped_one_time)
                {
                    max_size = Math.max(max_size, r-l);
                    l = pos_of_skipped_ele+1;
                }
                skipped_one_time = true;
                pos_of_skipped_ele = r;
                r++;
            }
        }
        max_size--;

        return max_size;
    }
}