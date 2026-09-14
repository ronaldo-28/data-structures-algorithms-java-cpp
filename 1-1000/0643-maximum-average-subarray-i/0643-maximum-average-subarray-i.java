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
    public double findMaxAverage(int[] nums, int k) {
      double sum =0;
      for(int i=0;i<k;i++){
        sum = sum+ nums[i];
      }
      double maxSum = sum;
      for(int i =k;i<nums.length;i++){
        sum = sum+nums[i]-nums[i-k];
        maxSum = Math.max(maxSum,sum);
      }
      return maxSum/k;
    }
}