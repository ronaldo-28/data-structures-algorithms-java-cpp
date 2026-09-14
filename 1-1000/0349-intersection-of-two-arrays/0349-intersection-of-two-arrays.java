class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        boolean[] seen = new boolean[1001];
        int idx = 0 ; 
        for(int num : nums1){
            seen[num]= true;
        }
        for(int num : nums2){
            if(seen[num]==true){
                nums1[idx++]= num;
                seen[num]=false;
            }
        }

        int[] ans = new int[idx];

        for(int i = 0 ; i<idx; i++){
            ans[i]=nums1[i];
        }

        return ans ; 

        
    }
}