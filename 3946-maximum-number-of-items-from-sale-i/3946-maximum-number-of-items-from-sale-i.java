// https://www.youtube.com/@0x3f
class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int maxFactor = 0;
        int minPrice = Integer.MAX_VALUE;
        for (int[] p : items) {
            maxFactor = Math.max(maxFactor, p[0]);
            minPrice = Math.min(minPrice, p[1]);
        }

        int[] cntFactor = new int[maxFactor + 1];
        for (int[] p : items) {
            cntFactor[p[0]]++;
        }
        int[] cntMulti = new int[maxFactor + 1];
        int[] f = new int[budget + 1];
        int sumPrice = 0;

        for (int[] p : items) {
            int factor = p[0], price = p[1];

            if (cntMulti[factor] == 0) {
                for (int j = factor; j <= maxFactor; j += factor) {
                    cntMulti[factor] += cntFactor[j];
                }
            }
            int cnt = cntMulti[factor];

            sumPrice = Math.min(sumPrice + price, budget);
            for (int j = sumPrice; j >= price; j--) {
                f[j] = Math.max(f[j], f[j - price] + cnt);
            }
        }

        int ans = 0;
        for (int i = 0; i <= budget; i++) {
            ans = Math.max(ans, f[i] + (budget - i) / minPrice);
        }
        return ans;
    }
}