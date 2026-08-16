class Solution {
    public int absDifference(int[] arr, int k) {
        countingSort(arr);
        int res=0;
        for (int i=0;i<k;i++) {
            res+=arr[i];
            res-=arr[arr.length-i-1];
        }
        return Math.abs(res);
    }

    public void countingSort(int[] arr){
        var cnts=new int[101];
        for (var num:arr) cnts[num]++;
        var resIdx=0;
        for (int i=0;i<cnts.length;i++) while (cnts[i]-->0) arr[resIdx++]=i;
    }
}