import java.util.Arrays;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        
        int maxFactor = 0;
        long cheapestPrice = Long.MAX_VALUE;
        
        for (int[] item : items) {
            maxFactor = Math.max(maxFactor, item[0]);
            cheapestPrice = Math.min(cheapestPrice, (long) item[1]);
        }

        if (cheapestPrice == 0) {
            return Integer.MAX_VALUE;
        }

        int[] factorFrequency = new int[maxFactor + 1];
        for (int[] item : items) {
            factorFrequency[item[0]]++;
        }
        int[] freebiesPerFactor = new int[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            if (factorFrequency[f] > 0) {
                int totalMultiples = 0;
                for (int m = f; m <= maxFactor; m += f) {
                    totalMultiples += factorFrequency[m];
                }
                freebiesPerFactor[f] = totalMultiples;
            }
        }

        long cutoffPrice = cheapestPrice * 2;

        long[] specialDeals = new long[n];
        int dealCount = 0;

        for (int[] item : items) {
            int factor = item[0];
            long price = item[1];
            
            int maxUses = freebiesPerFactor[factor] - 1;

            if (maxUses > 0 && price < cutoffPrice) {
                specialDeals[dealCount++] = (price << 20) | (long) maxUses;
            }
        }
        Arrays.sort(specialDeals, 0, dealCount);

        long remainingBudget = budget;
        long totalItemsPurchased = 0;

        for (int i = 0; i < dealCount; i++) {
            long bundle = specialDeals[i];
            long dealPrice = bundle >>> 20;
            long maxUses = bundle & 0xFFFFF;

            long affordableUses = remainingBudget / dealPrice;
            
            if (affordableUses == 0) {
                break;
            }
            
            long usesTaken = Math.min(maxUses, affordableUses);

            if (usesTaken > 0) {
                totalItemsPurchased += (usesTaken * 2); 
                remainingBudget -= (usesTaken * dealPrice);
            }
        }

        totalItemsPurchased += (remainingBudget / cheapestPrice);

        return (int) totalItemsPurchased;
    }
}