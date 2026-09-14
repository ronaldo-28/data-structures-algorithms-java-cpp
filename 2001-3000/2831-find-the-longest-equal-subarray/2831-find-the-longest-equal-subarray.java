class Solution {
    public int longestEqualSubarray(List<Integer> nums, int k) {
        int n = nums.size();
        int[] count = new int[n+1];
        int l = 0, maxFreq = 0;

        for(int r=0; r<n; r++){
            int num = nums.get(r);
            count[num]++;
            maxFreq = Math.max(maxFreq, count[num]);
            if((r-l+1) - maxFreq > k){
                count[nums.get(l)]--;
                l++;
            }
        }

        return maxFreq;
    }
}