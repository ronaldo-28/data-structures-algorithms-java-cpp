/**
 * Definition for BigArray.
 * class BigArray {
 *     public BigArray(int[] elements);
 *     public int at(long index);
 *     public long size();
 * }
 */
class Solution {
    public int countBlocks(BigArray nums) {
        long size = nums.size();
        return helper(0, size-1, nums.at(0), nums.at(size-1), nums);
    }
        int helper(long l, long r, int lval, int rval, BigArray nums){
        if(lval==rval) return 1;
        if(l==r-1) return 2;
        long mid = l+(r-l)/2;
        int mval = nums.at(mid);
        
        return helper(l, mid, lval, mval, nums) + helper(mid, r, mval, rval, nums) - 1 ;
    }
}