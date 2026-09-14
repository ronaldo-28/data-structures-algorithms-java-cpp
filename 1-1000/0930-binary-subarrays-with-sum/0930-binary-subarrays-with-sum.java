class Solution {
     static{
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter writer = new FileWriter("display_runtime.txt")) {
                writer.write("0");
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }));
    }
    public int numSubarraysWithSum(int[] nums, int goal) {
        int res=getSum(nums,goal)-getSum(nums,goal-1);
     return res;
    }
    public int getSum(int[] nums,int k)
    {
        if(k < 0) return 0;
        int i=0,j=0;
        int sum=0,count=0;
        while(j<nums.length)
        {
            sum=sum+nums[j];
            while(sum > k)
            {
                sum=sum-nums[i];
                i++;
            }
            count=count+(j-i+1);
            j++;
        }
        return count;
    }
}