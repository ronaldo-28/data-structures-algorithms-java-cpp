class Solution {
    public long minOperations(int[] nums, int k) {
        int n = nums.length;

        int[] freqE = new int[k], freqO = new int[k]; //calc frequencies % k of even and odd indices seperately
        for(int i = 0; i < n; i += 2) freqE[nums[i] % k]++;
        for(int i = 1; i < n; i += 2) freqO[nums[i] % k]++;


        long[] costE = calcCost(freqE, k, n + 1 >>> 1); //costE[i] = cost of changing all even indexed values to 'i'

        //find min and second min cost among all values 0 to k, as well as the best value to set all even elements to
        long minE = Long.MAX_VALUE / 2, secondMinE = Long.MAX_VALUE / 2;
        int bestE = -1;
        for(int i = 0; i < k; i++) {
            long cost = costE[i];

            if(cost < minE) {
                secondMinE = minE;
                minE = cost;
                bestE = i;
            }else if(cost < secondMinE) secondMinE = cost;
        }


        long[] costO = calcCost(freqO, k, n >>> 1); //costO[i] = cost of changing all odd indexed values to 'i'

        //find min and second min cost among all values 0 to k, as well as the best value to set all odd elements to
        long minO = Long.MAX_VALUE / 2, secondMinO = Long.MAX_VALUE / 2;
        int bestO = -1;
        for(int i = 0; i < k; i++) {
            long cost = costO[i];

            if(cost < minO) {
                secondMinO = minO;
                minO = cost;
                bestO = i;
            }else if(cost < secondMinO) secondMinO = cost;
        }


        //if the best value for both even and odd are equal, choose the second best instead for one of them
        if(bestE == bestO) return Math.min(minE + secondMinO, minO + secondMinE);
        return minE + minO;
    }

    //returns 'cost' array, 'cost[i]' is the min cost to change all elements in the frequency array to 'i'
    private long[] calcCost(int[] freq, int k, int totalCount) {
        long[] cost = new long[k]; //cost[i] is the sum of the distances from 'i' to each element

        //calc cost to change all elements to 0
        for(int i = 0; i < k; i++) cost[0] += (long)freq[i] * Math.min(i, k - i);

        //count the elements from 1 to k/2
        long count = 0;
        int half = k >>> 1;
        for(int i = 1; i <= half; i++) count += freq[i];

        if((k & 1) == 0) { //if k is even
            //as we go from 'i - 1' to 'i', cost changes in the following way:
            //the elements from 'i' to 'i + k/2' all have their distance decreased by 1
            //all other elements have their distance increased by 1

            //this means:
            //let 'count' be the count of elements from 'i' to 'i + k/2'
            //cost[i] = cost[i - 1] - count + (totalCount - count)
            //cost[i] = cost[i - 1] + totalCount - 2 * count

            //also, each step, 'count' increases by freq[i + k/2] and decreases by freq[i]
            
            for(int i = 1; i < k; i++) {
                cost[i] = cost[i - 1] + totalCount - 2 * count;
                count += freq[(i + half) % k] - freq[i];
            }
        }else { //if k is odd
            //almost the same as if k is even, with one important difference
            //as we go from 'i - 1' to 'i', the elements at 'i + k/2' dont get closer or further

            //for example, say k is 5, and 'i' goes from 1 to 2, the elements at 4 stay 2 moves away:
            //(4->0->1 | 4->3->2)
            
            for(int i = 1; i < k; i++) {
                cost[i] = cost[i - 1] + totalCount - 2 * count - freq[(i + half) % k];
                count += freq[(i + half) % k] - freq[i];
            }
        }
        return cost;
    }
}