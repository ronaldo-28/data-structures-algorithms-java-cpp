
  
  class Solution {
    public int[] addNegabinary(int[] arr1, int[] arr2) {
       int n=arr1.length,m=arr2.length,l=Math.max(n,m);
        int arr[]=new int[l+3],i=n-1,j=m-1,pp=l+2;
        while(i>=0)
        {
            arr[pp--]+=arr1[i--];
        }
        pp=l+2;
        while(j>=0)
        {
            arr[pp--]+=arr2[j--];
        }
        for(int k=l+2;k>=2;k--,i--,j--)
        {
            int vv=arr[k];
            if(vv==1)
            {
                arr[k]=1;
            }
            else if(vv==2)
            {
                 if(arr[k-1]>0)
                {
                arr[k-1]-=1;
                }
                else
                {
                    arr[k-1]+=1;
                    arr[k-2]+=1;
                }
                arr[k]=0;
            }
            else if(vv==3)
            {
                if(arr[k-1]>0)
                {
                arr[k-1]-=1;
                }
                else
                {
                    arr[k-1]+=1;
                    arr[k-2]+=1;
                }
                arr[k]=1;
            }
            else if(vv==4)
            {
                arr[k-2]+=1;
                arr[k]=0;
            }
            // for(int p=0;p<l+3;p++)
            // {
            // System.out.print(arr[p]+"  ");
            // }
            // System.out.println();
        }
        pp=0;
        while(pp<l+3 && arr[pp]==0)
        {
            pp++;
        }
        if(pp==l+3)
        {
            return new int[]{0};
        }
        int ans[]=new int[l+3-pp],st=0;
        while(pp<l+3)
        {
            ans[st++]=arr[pp++];
        }
        return ans;
    }
}
// 0 0 0 0 0 0 
//       1 1 0
//     1 2  0
//   1 1
  