class Solution {
    static{
        for(int i=0;i<500;i++){
            majorityElement(new int[0]);
        }
    }
    public static List<Integer> majorityElement(int[] nums) {
       List<Integer> ans=new ArrayList<>();
       int n=nums.length;
       int k=n/3;
       int count1=0;
       int count2=0;
       int ele1=Integer.MIN_VALUE;
       int ele2=Integer.MIN_VALUE;
       for(int i=0;i<n;i++){
        if(count1==0 && nums[i]!=ele2){
            count1=1;
            ele1=nums[i];
        }else if(count2==0 && nums[i]!=ele1){
            count2=1;
            ele2=nums[i];
        }else if(nums[i]==ele1){
            count1++;
        }else if(nums[i]==ele2){
            count2++;
        }else{
            count1--;
            count2--;
        }
       }
       count1=0;
       count2=0;
       for(int i=0;i<n;i++){
        if(ele1==nums[i])count1++;
        if(ele2==nums[i])count2++;
       }
        if(count1>k)ans.add(ele1);
        if(count2>k)ans.add(ele2);
       return ans;


        
    }
}