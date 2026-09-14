class Solution {
    public int function(int[] arr,int target, int mid){     
        int sum = 0;

        for(int i=0 ; i<arr.length ; i++){
            sum += Math.min(arr[i],mid);
        }

        return Math.abs(sum - target);
    }

    public int findBestValue(int[] arr, int target) {

        int n = arr.length;   
        int low = 0;
        int high = target;
        int mid = 0;
        int ans = 0;

        while(low <= high){
            mid = low + (high-low)/2;

            if(function(arr,target,mid) <= function(arr,target,mid+1)){
                high = mid-1;
                ans = mid;
            }

            else low = mid+1;

        }

        return ans;
    }
}