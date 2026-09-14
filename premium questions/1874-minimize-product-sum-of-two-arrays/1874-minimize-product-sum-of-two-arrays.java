class Solution {
    public int minProductSum(int[] nums1, int[] nums2) {
        int res = 0;
        int count1[] = new int[100];
        int count2[] = new int[100];


        for (int i = 0; i < nums1.length; i++){
            count1[nums1[i]-1]++;
            count2[nums2[i]-1]++;
        }

        int p1 = 0;
        int p2 = 99;
        while (p1 < 100 && p2 >= 0){
            if (count1[p1] == 0){
                p1++;
            } else if (count2[p2] == 0){
                p2--;
            } else {
                int min = count1[p1] < count2[p2] ? count1[p1] : count2[p2];
                res += (p1+1) * (p2+1) * min;
                count1[p1] -= min;
                count2[p2] -= min; 
            }
        }
        
        return res;
    }
}