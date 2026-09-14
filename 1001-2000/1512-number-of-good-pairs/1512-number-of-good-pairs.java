class Solution {
    public int numIdenticalPairs(int[] nums) {
        int pairs = 0, cnt[] = new int[101];
        for(int val: nums){
            pairs += cnt[val]++;
        } 
        return pairs;
    }
}