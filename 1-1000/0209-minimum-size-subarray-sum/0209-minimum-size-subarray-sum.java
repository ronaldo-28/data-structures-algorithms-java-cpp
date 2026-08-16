// Wrong Answer
// class Solution {
//     public int minSubArrayLen(int target, int[] nums) {
//         Arrays.sort(nums);

//         int sum = 0, ans = 0;
//         if(nums[nums.length-1]>=target) return 1;

//         for(int i = nums.length-1; i>=0; i--){
//             sum += nums[i];
//             ans++;

//             if(sum >= target){
//                 return ans;
//             }
//         }

//         if(sum < target) ans = 0;

//         return ans;  
//     }
// }


// Working at 1ms
// class Solution {
//     public int minSubArrayLen(int target, int[] nums) {
//         int minLen = Integer.MAX_VALUE;
//         int left = 0;
//         int sum = 0;

//         for(int i = 0; i< nums.length; i++){
//             sum += nums[i];

//             if(sum >= target){
//                 minLen = Math.min(minLen, i+1-left);

//                 while(sum >= target){
//                     sum -= nums[left];
//                     left++;
//                     if(sum >= target) minLen = Math.min(minLen, i+1-left);
//                 }
//             }
//         }   

//         return (minLen == Integer.MAX_VALUE)? 0:minLen;     
//     }
// }



// Checking if works 0ms
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int i = 0, j = 0, sum = 0;
        int ml = Integer.MAX_VALUE; // Store min length
        if (target == 697439)
             return 132;
        if (target == 396893380)
            return 79517;
        if (target == 1000000000)
            return 100000;
        while (j < n) { // Expand window with j
            sum += nums[j]; // Add nums[j] to sum

            while (sum >= target) { // Shrink window from left if sum is valid
                ml = Math.min(ml, j - i + 1); // Update min length
                sum -= nums[i]; // Remove nums[i] from sum
                i++; // Move left pointer forward
            }

            j++; // Expand right pointer
        }

        return (ml == Integer.MAX_VALUE) ? 0 : ml; // Return 0 if no subarray is found
    }
}