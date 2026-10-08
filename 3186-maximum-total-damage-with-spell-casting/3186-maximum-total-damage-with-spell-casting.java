class Pair {
    int num;
    long sum;
    public Pair(int num, long sum) {
        this.num = num;
        this.sum = sum;
    }
}
class Solution {
    public long maximumTotalDamage(int[] power) {
        Arrays.sort(power);
        //TreeMap<Integer, Long> dp = new TreeMap<>();
        //dp.put(Integer.MIN_VALUE, 0L);
        Pair p1 = new Pair(Integer.MIN_VALUE, 0), p2 = new Pair(Integer.MIN_VALUE, 0), p3 = new Pair(Integer.MIN_VALUE, 0);

        int n = power.length, cnt = 0;
        long cur = 0;
        for (int i = 0; i < n; i++) {
            cnt++;
            if (i == n - 1 || power[i] != power[i + 1]) {
                long pick = 0;
                if (power[i] - p3.num > 2) {
                    pick = p3.sum;
                } else if (power[i] - p2.num > 2) {
                    pick = p2.sum;
                } else if (power[i] - p1.num > 2){
                    pick = p1.sum;
                }
                pick += 1L * cnt * 1L * power[i];
                long notPick = Math.max(p1.sum, Math.max(p2.sum, p3.sum));
                p1 = p2;
                p2 = p3;
                p3 = new Pair(power[i], Math.max(pick, notPick));               
                cnt = 0;
            }
        }

        return p3.sum;
    }
}