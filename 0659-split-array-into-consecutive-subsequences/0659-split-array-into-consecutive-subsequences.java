class Solution {
    public boolean isPossible(int[] nums) {
        int l1=0,l2=0,l3=0;
        for(int i=0;i<nums.length;i++){
            boolean consec=false;
            if(i>0&&nums[i]==nums[i-1]+1) consec=true;
            int cnt=1;
            while(i+1<nums.length&&nums[i]==nums[i+1]){
                cnt++;
                i++;
            }
            if(!consec){
                if(l1>0||l2>0) {
                    return false;
                }
                l3=0;
                l1=cnt;
            }else{
                if(l1+l2>cnt) return false;
                int x2=l1;
                int x3=Math.min(cnt-l1,l2+l3);
                int x1=cnt-x3-x2;
                l1=x1;
                l2=x2;
                l3=x3;
            }
        }
        return l1==0&&l2==0;
    }
}