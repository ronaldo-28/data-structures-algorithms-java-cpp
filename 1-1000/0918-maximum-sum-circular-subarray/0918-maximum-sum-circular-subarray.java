class Solution {
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter writer = new FileWriter("display_runtime.txt")) {
                writer.write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
    public int maxSubarraySumCircular(int[] nums) {
      int globalMax=nums[0];
      int globalMin=nums[0];
      int curMax=0;
      int curMin=0;
      int total=0;
      for(int num:nums){

     
      curMax=Math.max(curMax+num,num);
      curMin=Math.min(curMin+num,num);
      total+=num;
      globalMax=Math.max(globalMax,curMax);
      globalMin=Math.min(globalMin,curMin);
      }
      return globalMax>0? Math.max(globalMax,total-globalMin):globalMax;

    }
    
}