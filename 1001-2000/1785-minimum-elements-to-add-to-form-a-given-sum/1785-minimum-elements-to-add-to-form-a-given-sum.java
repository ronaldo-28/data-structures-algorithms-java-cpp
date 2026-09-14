class Solution {
    public int minElements(int[] nums, int limit, int goal) {
        long s=0;
        for(int i:nums){
            s+=i;
        }
        long diff=Math.abs(goal-s);
        double me=Math.ceil((double)(diff)/limit);
        return (int)me;

    }
}