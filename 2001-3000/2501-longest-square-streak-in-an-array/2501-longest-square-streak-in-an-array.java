class Solution {
    public int longestSquareStreak(int[] nums) {

        HashSet<Integer> set = new HashSet<>();

        for(int n : nums){
            set.add(n);
        }

        int ans = 0;

        for(int n : nums){

            int curr = n;
            int count = 1;

            while((long)curr * curr <= 1000000000 
                  && set.contains(curr * curr)) {

                curr = curr * curr;
                count++;
            }

            ans = Math.max(ans, count);
        }

        return ans >= 2 ? ans : -1;
    }
}