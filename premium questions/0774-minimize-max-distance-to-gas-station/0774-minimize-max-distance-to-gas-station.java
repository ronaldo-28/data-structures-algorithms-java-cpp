class Solution {
    public double minmaxGasDist(int[] stations, int k) {
        double low = 0, high = 1_000_000;
        
        while (low + 0.00001 < high) {
            double mid = low + (high - low) / 2;
            if (check(stations, mid, k)) {
                high = mid;
            } else {
                low = mid;
            }
        }


        return low;
    }

    boolean check(int[] stations, double penalty, int k) {
        for (int i = 1; i < stations.length; i++) {
            //double need = Math.ceil((stations[i] - stations[i - 1]) / penalty) - 1; (below is cleaner, instead of subtracting 1, just do floor division, which casting to int will handle)
            int need = (int) ((stations[i] - stations[i - 1]) / penalty);
            k -= need;
        }
        return k >= 0;
    }
}