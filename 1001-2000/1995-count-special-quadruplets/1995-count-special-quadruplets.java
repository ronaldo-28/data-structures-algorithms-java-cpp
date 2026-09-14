class Solution {
    public int countQuadruplets(int[] nums) {
        int n = nums.length;
        int count = 0;
    
        int[] diffCount = new int[400]; 

        for (int b = n - 3; b >= 1; b--) {
            int c = b + 1;
            
            for (int d = c + 1; d < n; d++) {
                int diff = nums[d] - nums[c];
                diffCount[diff + 100]++; 
            }
            
            for (int a = 0; a < b; a++) {
                int sum = nums[a] + nums[b];
                
                count += diffCount[sum + 100]; 
            }
        }
        
        return count;
    }
}