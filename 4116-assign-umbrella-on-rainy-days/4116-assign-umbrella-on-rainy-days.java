class Solution {
    public String[] assignUmbrella(int[] rain) {
        // Intuition: simulate, track only d = bob - alice
        int n = rain.length;
        int d = 0;
        String[] r = new String[n];
        for (int i = 0; i < n; i++) {
            if (rain[i] == 0) {
                r[i] = "None";
                continue;
            }
            // Only Bob needs it
            if (i == n - 1 || rain[i + 1] == 0) {
                r[i] = "Bob";
                d++;
                continue;
            }
            // Both need it, tie goes to Alice
            if (d >= 0) {
                r[i] = "Alice";
                d--;
                continue;
            }
            r[i] = "Bob";
            d++;
        }
        return r;
    }
}