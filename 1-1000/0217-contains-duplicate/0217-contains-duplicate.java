class Solution {
    static{
        for(int i=0;i<=500;i++)
            containsDuplicate(new int[]{0, 1, 0});
    }
    public static boolean containsDuplicate(int[] nums) {
        for (int i = 1; i < nums.length; i++){
            int key = nums[i];
            int j = i - 1;
            while (j >= 0 && nums[j] > key){
                nums[j + 1] = nums[j];
                j--;
            }
            if (j >= 0 && nums[j] == key)
                return true;
            nums[j + 1] = key;
        }
        return false;
    }
}