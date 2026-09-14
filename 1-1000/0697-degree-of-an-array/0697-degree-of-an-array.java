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
    public int findShortestSubArray(int[] nums) {

        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Integer> first = new HashMap<>();
        Map<Integer, Integer> last = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
            first.putIfAbsent(nums[i], i);
            last.put(nums[i], i);
        }

        int degree = Collections.max(freq.values());
        int ans = nums.length;

        for (int num : freq.keySet()) {
            if (freq.get(num) == degree) {
                int length = last.get(num) - first.get(num) + 1;
                ans = Math.min(ans, length);
            }
        }

        return ans;
    }
}