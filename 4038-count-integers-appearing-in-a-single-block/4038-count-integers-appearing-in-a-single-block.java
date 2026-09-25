class Solution {
    public int countSpecialIntegers(int[] nums) {
        Map<Integer, Integer> mp = new HashMap<>();

        int n = nums.length;

        mp.put(nums[0], 1);

        for(int i = 1; i < n; i++){
            if(nums[i] != nums[i - 1]){
                mp.put(nums[i], mp.getOrDefault(nums[i], 0) + 1);
            }
        }

        int cnt = 0;

        for(Map.Entry<Integer, Integer> p : mp.entrySet()){
            if(p.getValue() == 1){
                cnt++;
            }
        }

        return cnt;
    }
}