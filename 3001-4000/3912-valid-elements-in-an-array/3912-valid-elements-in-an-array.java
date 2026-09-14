class Solution {
    public List<Integer> findValidElements(int[] nums) {
        int n=nums.length;
        int leftgreat[]=new int[n];
        int rightgreat[]=new int[n];
        int lmax=nums[0];
        int rmax=nums[0];
        List<Integer> list=new ArrayList<>();
        if(n==1){
            list.add(nums[0]);
            return list;
        }
        for(int i=0;i<n;i++){
            if(i==0){
                leftgreat[i]=0;
            }
            else{
                leftgreat[i]=Math.max(nums[i-1],leftgreat[i-1]);
            }
            int j=n-i-1;
            if(n-1==j){
                rightgreat[j]=0;
            }
            else{
                rightgreat[j]=Math.max(nums[j+1],rightgreat[j+1]);
            }
        }
        for(int i=0;i<n;i++){
            if(nums[i]>leftgreat[i]||nums[i]>rightgreat[i]||i==0||i==n-1){
                list.add(nums[i]);
            }
        }
        return list;
        
    }
}