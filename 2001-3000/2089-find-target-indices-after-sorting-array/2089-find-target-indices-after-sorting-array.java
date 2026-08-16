class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int smaller= 0;
        int equal = 0;

        for(int num : nums){
            if(num < target) smaller++;
            if(num == target) equal++;
        }
        List<Integer> arr = new ArrayList<>();
        for(int i=0;i<equal;i++){
            arr.add(smaller + i);
        }

        return arr;
        
    }
}