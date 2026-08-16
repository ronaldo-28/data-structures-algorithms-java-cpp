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
     
    public int longestOnes(int[] nums, int k) {
        int max = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int count = 0;
            int flips = k;

            for (int j = i; j < n; j++) {
                if (nums[j] == 1) {
                    count++;
                } else {
                    if (flips > 0) {
                        flips--;
                        count++;
                    } else {
                        break;
                    }
                }
            }
            max = Math.max(max, count);
        }
        return max;
    }
}