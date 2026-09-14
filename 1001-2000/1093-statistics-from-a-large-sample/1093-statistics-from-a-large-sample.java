class Solution {
    public double[] sampleStats(int[] counts) {
        double[] arr = new double[5];
        long sum = 0;
        int max = 0, max_val = 0, i_max = 0, low = -1, n = 0;
        int n_counts = counts.length;
        for(int i = 0; i < n_counts; i++) {
            if(counts[i] != 0) {
                if(counts[i] > max_val) {
                   max = i;
                   max_val = counts[i];
                }
                if(low == -1) 
                   low = i;
                i_max = i;
                sum += (long)i * counts[i];
                n += counts[i];
            }
        }
        int far = 0;
        if(n % 2 != 0) {
            for(int i = low; i <= i_max; i++) {
                far += counts[i];
                if(far >= n/2 + 1) {
                   arr[3] = i;
                   break;
                }
            }
        } else {
            for(int i = low; i <= i_max; i++) {
                far += counts[i];
                if(far >= n/2 + 1) {
                   arr[3] = i;
                   break;
                } else if(far == n/2) {
                    for(int k = i+1; k <= i_max; k++) {
                        if(counts[k] != 0) {
                           arr[3] = (i + k) / 2.0;
                           break;
                        }
                    }
                    break;
                }
            }
        }
        arr[0] = low;
        arr[1] = i_max;
        arr[2] = (double)sum / n;
        arr[4] = max;
        return arr;
    }
}