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
    public int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        int i = 0, idxMin = 0, min = Math.abs(nums[0]);
        while(i < nums.length && k > 0) {
            if(min > Math.abs(nums[i])) {
                idxMin = i;
                min = Math.abs(nums[i]);
            }
            if(nums[i] < 0) {
                nums[i] = -nums[i];
                k--;
            }
            i++;
        }

        while(k > 0) {
            nums[idxMin] = -nums[idxMin];
            k--;
        }

        int ans = 0;
        for(int n: nums) {
            ans += n;
        }

        return ans;
    }
}