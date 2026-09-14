class Solution {
   public int[] recoverArray(int[] nums) {
       Arrays.sort(nums);
       int[] res = new int[nums.length / 2];
       int prev = 0; // used to process each diff of 2*k only once
       // try finding a pair element x+2*k for the smallest one x
       for (int i = 1; i < nums.length; i++) {
           int diff = nums[i] - nums[0]; // 2*k, must be positive and even
           if (diff != prev && diff > 0 && diff % 2 == 0 && check(nums, i, diff / 2, res)) break;
           prev = diff;
       }
       return res;
   }
   
    // j points to the higher element of the pair
   private boolean check(int[] nums, int j, int k, int[] res) {
       int idx = 0;
       boolean[] visited = new boolean[nums.length];
       // i points to the lower element of the pair
       for (int i = 0; i < nums.length; i++) {
           if (visited[i]) continue;
           visited[i] = true;
           int target = nums[i] + 2 * k;
           // find the target = the higher element of the pair
           while (j < nums.length && (nums[j] < target || (nums[j] == target && visited[j]))) j++;
           if (j == nums.length || nums[j] != target) return false;
           visited[j] = true;
           // both elements of the pair are confirmed, update the result
           res[idx++] = nums[i] + k;
       }
       return true;
   }
}