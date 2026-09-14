class Solution {
    public int missingMultiple(int[] nums, int k) {
        boolean arr[] = new boolean[101];
        for(int n : nums)
            arr[n] = true;
        
        int num = k;
        while(k < 101 && arr[k])
            k += num;
        
        return k;
    }
}