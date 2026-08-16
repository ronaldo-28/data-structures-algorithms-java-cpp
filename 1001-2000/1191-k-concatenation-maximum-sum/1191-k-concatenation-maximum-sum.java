class Solution {
public int kConcatenationMaxSum(int[] arr, int k) {
        long sum=0; long max=Integer.MIN_VALUE; long total=0;  long a=2;
       if(k==1){
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
            if(sum>max)max=sum;
            if(sum<0)sum=0;
        }
        if(max<0)return 0;
        return (int)max; 
       }
       sum=0;
       for(int num:arr)sum+=num;
       if(sum<0){
        sum=0;
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
            if(sum>max)max=sum;
            if(sum<0)sum=0;
        }
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
            if(sum>max)max=sum;
            if(sum<0)sum=0;
        } 
        if(max<0)return 0;      
        return (int)max%1000000007;
       }
       else{
        total=((k-2)%1000000007*sum%1000000007)%1000000007;
        sum=0;
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
            if(sum>max)max=sum;
            if(sum<0)sum=0;
        }
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
            if(sum>max)max=sum;
            if(sum<0)sum=0;
        }
        max=(total%1000000007+max)%1000000007;
        if(max<0)return 0;        
        return (int)max%1000000007;
    }
    }
}