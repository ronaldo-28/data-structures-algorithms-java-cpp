class Solution {

    public int minSwaps(int[] nums) {
        int n = nums.length;
        long[] packed = new long[n];

        // 1. Optimized digit sum calculation with reduced branch overhead
        for (int i = 0; i < n; i++) {
            int val = nums[i];
            int sum = 0;
            int temp = val;

            // Unroll loop for speed
            while (temp >= 10) {
                sum += temp % 10;
                temp /= 10;
            }
            sum += temp;

            // Bit layout: [Digit Sum (12b) | Value (32b) | Index (20b)]
            packed[i] = ((long) sum << 52) | ((long) val << 20) | i;
        }

        // 2. Custom Dual-Pivot Quicksort optimized for primitive long[]
        quickSort(packed, 0, n - 1);

        // 3. In-place Cycle Detection (Zero Extra Memory Allocation)
        int swaps = 0;

        for (int i = 0; i < n; i++) {
            // Check if element has already been visited (marked with bit 63)
            if ((packed[i] & (1L << 63)) != 0) {
                continue;
            }

            int cycleSize = 0;
            int curr = i;

            while ((packed[curr] & (1L << 63)) == 0) {
                // Mark as visited in-place using bit 63
                packed[curr] |= (1L << 63);
                
                // Extract original index from lower 20 bits
                curr = (int) (packed[curr] & 0xFFFFF);
                cycleSize++;
            }

            if (cycleSize > 1) {
                swaps += (cycleSize - 1);
            }
        }

        return swaps;
    }

    // High-performance Dual-Pivot Quicksort
    private void quickSort(long[] a, int left, int right) {
        if (right - left < 27) { // Insertion sort threshold for sub-arrays
            for (int i = left + 1; i <= right; i++) {
                long key = a[i];
                int j = i - 1;
                while (j >= left && a[j] > key) {
                    a[j + 1] = a[j];
                    j--;
                }
                a[j + 1] = key;
            }
            return;
        }

        if (a[left] > a[right]) {
            swap(a, left, right);
        }

        long p = a[left];
        long q = a[right];

        int l = left + 1;
        int g = right - 1;
        int k = l;

        while (k <= g) {
            if (a[k] < p) {
                swap(a, k, l);
                l++;
            } else if (a[k] >= q) {
                while (a[g] > q && k < g) {
                    g--;
                }
                swap(a, k, g);
                g--;
                if (a[k] < p) {
                    swap(a, k, l);
                    l++;
                }
            }
            k++;
        }
        l--;
        g++;

        swap(a, left, l);
        swap(a, right, g);

        quickSort(a, left, l - 1);
        quickSort(a, l + 1, g - 1);
        quickSort(a, g + 1, right);
    }

    private void swap(long[] a, int i, int j) {
        long temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}