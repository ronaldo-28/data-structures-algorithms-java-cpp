class Solution {
    public long numberOfPairs(int[] nums1, int[] nums2, int k) {
        int max=0,n=nums1.length,m=nums2.length;
        long count=0;
        for(int i=0;i<n;i++)
        {
            max=Math.max(nums1[i],max);
        }
        int freq[]=new int[max+1];
        for(int i=0;i<n;i++)
        {
            freq[nums1[i]]++;
        }
        for(int i=0;i<m;i++)
        {
            if(nums2[i]==1 && k==1) 
            {
                count+=m;
                continue;
            }
            for(int j=nums2[i]*k;j<=max;j+=nums2[i]*k)
            {
                count=count+freq[j];
            }
        }
        return count;
    }
}