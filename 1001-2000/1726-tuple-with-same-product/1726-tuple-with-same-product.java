class Solution {
    public int tupleSameProduct(int[] nums) {   
        int n=nums.length;
        int N=n*(n-1)/2;
        int[] q=new int[N];
        int idx=0;
        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                q[idx]=nums[i]*nums[j];
                idx++;
            }
        }
        Arrays.sort(q);
        int ans=0;
        int f=1;
        for(int i=1;i<N;i++){
            if(q[i]==q[i-1]){
                f++;
            }
            else{
                if(f>1){
                    ans+=f*(f-1)*4;
                }
                f=1;
            }
        }
        if(f>1){
                    ans+=f*(f-1)*4;
                }
        return ans;
    }
}