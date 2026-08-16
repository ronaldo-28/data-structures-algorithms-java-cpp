class Solution {
    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;

        int boundary = (int) Math.sqrt(n);
        for (int i = 3; i <= boundary; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }
    public boolean primeSubOperation(int[] nums) {
        int n = nums.length;
        boolean found = false;
        for(int i=n-1;i>0;i--){
            if(nums[i-1] >= nums[i]){
                int diff = nums[i-1] - nums[i];
                found = false;
                for(int j=diff+1;j<=nums[i-1];j++){
                    if(isPrime(j)) {
                        found = true;
                        nums[i-1] -= j;
                        if(nums[i-1] <= 0) return false;
                        break;
                    }
                }
                if(!found) return false;
            }
        }
        return true;
    }
}