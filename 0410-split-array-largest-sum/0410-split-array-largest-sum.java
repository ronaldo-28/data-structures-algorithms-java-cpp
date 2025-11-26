class Solution {
    public static boolean isPossible(int[] arr,int mid, int d){
        int count=1;
        int sum=0;
        for(int i: arr){
            sum += i;
            if(sum>mid){
                count++;
                sum = i;
            }
            if(count>d){
                return false;
            }
        }
        return true;
    }
    public int splitArray(int[] arr, int k) {
        int n = arr.length;

        int min = 0;
        int max = 0;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            min = Math.max(min, arr[i]);
            max += arr[i];
        }
        while (min <= max) {
            int mid = (min + max) / 2;
            if (isPossible(arr, mid, k)) {
                ans = mid;
                max = mid - 1;
            }
            else{
                min = mid + 1;
            }
        }
        return ans;
    }
}