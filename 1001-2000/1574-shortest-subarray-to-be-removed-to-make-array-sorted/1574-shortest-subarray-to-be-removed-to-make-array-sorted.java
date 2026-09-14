class Solution {
    public int findLengthOfShortestSubarray(int[] arr) {
        int n = arr.length;
        int start = 0;
        for(int i=0;i<n-1;i++){
            if(arr[i] <= arr[i+1]){
                start++;
            }else{
                break;
            }
        }
        if(start == n-1) return 0;
        int end = n-1;
        for(int i=n-1;i>start;i--){
            if(arr[i] >= arr[i-1]){
                end--;
            }else{
                break;
            }
        }
        
        int len = Math.min(n-start-1,end);

        for(int i=0,j=end;i<=start && j < n; ){
            if(arr[i] <= arr[j]){
                len = Math.min(len,j-i-1);
                i++;
            }
            else j++;
        }

        return len;
    }
}