class Solution {
    record state(int prev, int curr){

        public int getDiff(int[] nums){
            return nums[this.curr] - nums[this.prev];
        }
    }
    public int numberOfArithmeticSlices(int[] nums) {

        int len = nums.length;
        int[][] dp = new int[len][len];

        for (int i = 0; i < len; i++){
            Arrays.fill(dp[i], 0);
        }

        int total = 0;

        Map<Integer, List<Integer>> numLists = new HashMap<>();

        for (int i = 0; i < len; i++){
            int num = nums[i];
            numLists.computeIfAbsent(num, (a) -> new ArrayList<Integer>()).add(i);
        }


        for (int i = len - 1; i > -1; i--){
            for (int j = i - 1; j > -1; j--){

                int curr = nums[i];
                int prev = nums[j];
                long diff = (long)curr - prev;

                int sumThis = 0;

                long dNext = (long)curr + diff;

                if (dNext > Integer.MAX_VALUE || dNext < Integer.MIN_VALUE) continue;

                int desiredNext = (int)dNext;
                int nextI = -1;

                if (!numLists.containsKey(desiredNext)){
                    continue;
                }
                List<Integer> targLis = numLists.get(desiredNext);
                nextI = bSearch(targLis, i);
                
                if (nextI == -1) continue;

                while (nextI < targLis.size()){
                    sumThis += dp[targLis.get(nextI)][i] + 1;
                    nextI++;
                }

                total += sumThis;

                dp[i][j] = sumThis;
            }
        }

        return total;
    }

    private int bSearch(List<Integer> lis, int min){
        int lo = 0, hi = lis.size() - 1;

        int best = -1;
        while (lo <= hi){
            int mid = lo + (hi - lo) / 2;

            if (lis.get(mid) > min){
                best = mid;
                hi = mid - 1;
            }
            else lo = mid + 1;
        }

        return best;
    }
}