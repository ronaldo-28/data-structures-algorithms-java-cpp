class Solution {
    public int[] createTargetArray(int[] nums, int[] index) {
        int[] arr=new int[nums.length];
        List<Integer> lt=new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            lt.add(index[i],nums[i]);
        }
        for(int i=0;i<nums.length;i++){
            arr[i]=lt.get(i);
        }
        return arr;
    }
}