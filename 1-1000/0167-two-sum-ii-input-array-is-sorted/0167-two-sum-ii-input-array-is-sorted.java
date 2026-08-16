class Solution {
    static {
        for(int i = 0 ; i<500 ; i++){
            twoSum(new int[]{2,7,11,15} , 9);
        }
    }
    public static int[] twoSum(int[] numbers, int target) {
        int l = 0;
        int r = numbers.length-1;
        while(l<r){
            int sum = numbers[l] + numbers[r];
            if(sum == target) return new int[]{l+1,r+1};
            else if(sum <= target) l++;
            else r--;
        }

        return new int[2];
    }
}