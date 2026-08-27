class Solution {
    public boolean containsPattern(int[] arr, int m, int k) {
        int n = arr.length;
        int len = m*k;
        for(int i=0;i<=n-len;i++)
            {
                boolean flag = true;
                 for(int j=i;j<i+len-m;j++)
                    {
                        if(j+m<n && arr[j]!=arr[j+m])flag=false;
                    }
                if(flag)return true;
            }
        return false;
    }
}