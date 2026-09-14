class Solution {
    private static final int[] freq = new int[100001];
    public int mostFrequentEven(int[] nums) {
        for(int num : nums) freq[num] = 0;
        int min = 0, index = 0;
        for(int num : nums) {
            if((num & 1) == 1) continue;
            if(freq[num]++ == min) {
                min++;
                index = num;
            }else if(freq[num] == min && index > num) index = num;
        }
        return min == 0 ? -1 : index;
    }
}