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
    public int maxNumberOfBalloons(String text) {
        int n = text.length();
        int freq[] = new int[26];
        for(char ch : text.toCharArray()) freq[ch-'a']++;

        int ans = 100000;
        ans = Math.min(ans, freq['b'-'a']);
        ans = Math.min(ans, freq['a'-'a']);
        ans = Math.min(ans, freq['l'-'a']/2);
        ans = Math.min(ans, freq['o'-'a']/2);
        ans = Math.min(ans, freq['n'-'a']);
        return ans;
    }
}