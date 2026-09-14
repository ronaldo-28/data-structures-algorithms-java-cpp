class Solution {
    public long countRatioSubarrays(int[] nums, int a, int b) {
        int n = nums.length;
        long[] v = new long[n+1];
        long x = 0,y=0;
        for(int i =0;i<n;i++){
            if(nums[i]%2==0) x++; else y++;
            v[i+1]=x*b-y*a;
        }
        return mergeSort(v,new long[n+1],0,n);
    }
    private long mergeSort(long[] a,long[] t,int l,int r){
        if(l>=r) return 0;
        int m = (l+r)/2,i=l,j=m+1,k=l;
        long res = mergeSort(a,t,l,m)+mergeSort(a,t,m+1,r);
        while(i<=m && j<=r){
            if(a[i]>=a[j]){
                res+=m-i+1;
                t[k++]=a[j++];
            }else{
                t[k++]=a[i++];
            }
        }
        while(i<=m) t[k++]=a[i++];
        while(j<=r) t[k++]=a[j++];
        System.arraycopy(t,l,a,l,r-l+1);
        return res;
    }
}