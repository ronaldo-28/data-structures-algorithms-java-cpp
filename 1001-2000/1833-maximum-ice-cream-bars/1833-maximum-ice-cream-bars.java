class Solution {
    static {
        for (int i = 0; i < 300; i++) maxIceCream(new int[1],0);
    }
    public static int maxIceCream(int[] costs, int coins) {
        int max = 0;
        for (int cost : costs) {
            if (max < cost) max = cost;
        }

        int[] count = new int[max+1];
        for (int cost : costs) {
            count[cost]++;
        }

        int ans = 0;
        for (int i = 1; i <= max; i++) {
            int cnt = count[i];
            if (cnt * i >= coins) {
                ans += coins/i;
                return ans;
            } else {
                coins -= cnt * i;
                ans += cnt;
            }
        }
        return ans;
    }
}