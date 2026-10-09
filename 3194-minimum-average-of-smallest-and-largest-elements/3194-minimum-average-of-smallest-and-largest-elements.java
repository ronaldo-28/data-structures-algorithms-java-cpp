class Solution {
    public double minimumAverage(int[] nums) {
        int n = nums.length;

        for(int i=0 ; i<n-1 ; i++){
            for(int j=0 ; j<n-1 ; j++){
                if(nums[j+1]<nums[j]){
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                }
            }
        }

        double avg;
        double min = 50;
        for(int i=0 ; i<n/2 ; i++){
            avg = (double)(nums[i]+nums[n-i-1])/2;
            min = Math.min(min,avg);
        }

        return min;
    }
}