class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int[] arrIdx = new int[10001];

        for(int i = 0;i <n2;i++){
            arrIdx[nums2[i]] = i;
        }

        for(int i = 0;i < n1;i++){
            nums1[i] = findNextgreater(nums2,nums1[i],arrIdx,n2);
        }

        return nums1;
    }

    public int findNextgreater(int[] arr,int greater,int[] arrIdx,int n){

        for(int i = arrIdx[greater] + 1;i < n;i++){
            if(arr[i] > greater){
                return arr[i];
            }
        }

        return -1;
    }
}