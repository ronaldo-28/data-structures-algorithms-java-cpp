class Solution {
    public int maxNumberOfAlloys(int n, 
                                 int k, 
                                 int budget,          
                                 List<List<Integer>> composition, 
                                 List<Integer> stock, 
                                 List<Integer> cost) {
        var stocks = toArray(stock);
        var costs = toArray(cost);

/* 
         * upper bound estimate on the output: 
         * limited by the cost of metal, 
         * assumes that each unit of metal has cost 1
         */
        int upperBound = budget;  
        for(int qty : stocks) {
            upperBound += qty;
        }

        int result = 0;
        for(int i=0; i<k; ++i) {
            int maxNbr = maxNumberOfAlloys(budget, 
                                           toArray(composition.get(i)), 
                                           stocks, 
                                           costs, 
                                           result,
                                           upperBound);
            result = Math.max(result, maxNbr);
        }

        return result;
    }


    private static int maxNumberOfAlloys(int budget, 
                                         int[] composition, 
                                         int[] stock, 
                                         int[] cost, 
                                         int low, 
                                         int high) {
        int n = cost.length;

        while(low<=high) {
            int mid = (low + high) >>> 1;
            long totalCost = 0;
            for(int i=0; i<n; ++i) {
                long required = (long)composition[i] * mid;
                totalCost += Math.max(0, required - stock[i]) * (long)cost[i];
                if(totalCost>budget)  break;
            }

            if(totalCost>budget) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return high;
    }

    private static int[] toArray(List<Integer> values) {
        int n = values.size();
        var result = new int[n];

        for(int i=0; i<n; ++i) {
            result[i] = values.get(i);
        }

        return result;
    }
}