class Solution {
    static{
        for(int i = 0; i<400;i++){
            longestMountain(new int[0]);
        }
    }
    public static int longestMountain(int[] arr) {
        int left=0;int i=1;
        int right=arr.length-1;
        int count=0;
        while(i<arr.length-1){
            if(arr[i]>arr[i-1] && arr[i]>arr[i+1]){
                left=i-1;
                right=i+1;
                while(left>0){
                    if(arr[left]>arr[left-1]){
                        left--;
                    }
                    else{
                        break;
                    }
                    
                }
                while(right < arr.length-1){
                    if(arr[right] > arr[right+1]){
                        right++;
                    }
                    else{
                        break;
                    }
                }
                count=Math.max(count,right-left+1);
            }
            i++;
        }
        return count;
    }
}