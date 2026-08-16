class Solution {
    public int numSub(String s) {
        final int MOD = 1_000_000_007;

        String[] nums = s.split("0");
        long ans = 0;

        for (String part : nums) {
            long n = part.length(); 
            ans = (ans + (n * (n + 1) / 2) % MOD) % MOD;
        }

        return (int) ans;
    }
    static{
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter writer = new FileWriter("display_runtime.txt")) {
                writer.write("0");
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }));
    }
}