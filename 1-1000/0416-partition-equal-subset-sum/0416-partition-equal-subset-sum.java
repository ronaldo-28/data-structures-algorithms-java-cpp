class Solution {
    public boolean canPartition(int[] nums) {
        int sum=0;
        for (int n: nums) {
            sum+=n;
        }

        if (sum%2!=0) {
            return false;
        }

        int target=sum/2;
        BitSet res=new BitSet(target+1);
        res.set(target);
        int i=0;
        while (i<nums.length && !res.get(0) && nums[i]<=target) {
            res.or(res.get(nums[i++], target+1));
        }

        return res.get(0);
    }
}