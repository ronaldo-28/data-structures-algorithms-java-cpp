class Solution {
    public int largestInteger(int num) {
        int t=num;
        int count=0;
        while(t!=0){
            t/=10;
            count++;
        }
        int nums[]= new int[count--];
        while(num!=0){
            nums[count--]=num%10;
            num/=10;
        }
        for(int i=0;i<nums.length;i++){
            int maxind=i;
            if(nums[i]%2==0){
                for(int j=i;j<nums.length;j++){
                if(nums[j]%2==0 && nums[j]>nums[maxind])
                    maxind=j;
            }
            }
            else{
            for(int j=i;j<nums.length;j++){
                if(nums[j]%2!=0 && nums[j]>nums[maxind])
                    maxind=j;
            }}
            int temp=nums[i];
            nums[i]=nums[maxind];
            nums[maxind]=temp;
        }
        int result=0;
        int digit=nums.length-1;
        for(int i=0;i<nums.length;i++){
            result+=nums[i]*Math.pow(10,digit--);
        }
        return result;
        /*ArrayList<Integer> odd=new ArrayList<>();
        ArrayList<Integer> even=new ArrayList<>();
        int maxeven=,maxodd=-1;
        for(int i=0;i<num.length;i++){
            if(num[i]%2==0){
                even.add(num[i])
            } 
            else odd.add(num[])
        }*/
    }
}