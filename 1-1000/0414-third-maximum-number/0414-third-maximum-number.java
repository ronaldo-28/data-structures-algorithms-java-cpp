class Solution {
    public int thirdMax(int[] nums) {
        long first = Long.MIN_VALUE;
        long second = Long.MIN_VALUE;
        long third = Long.MIN_VALUE;
        
        for(int num : nums) {
            if(num > first && first != num) {
                third = second;
                second = first;
                first = num;
            } else if(num > second && second != num && num < first) {
                third = second;
                second = num;
            } else if(num > third && third != num && num < second) {
                third = num;
            }
        }
        
        return third == Long.MIN_VALUE ? (int)first : (int)third;
    }
}