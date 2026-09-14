import java.util.Arrays;

class Solution {

    public int halveArray(int[] nums) {
        int totalSteps = 0;
        int n = nums.length;

        MaxHeap heap = new MaxHeap(n);
        double totalSum = 0;
        for (int x : nums) {
            heap.insert(x);            // primitive insert
            totalSum += x;
        }

        double target = totalSum / 2.0;
        while (totalSum > target) {
            double x = heap.poll();    // get current maximum
            double half = x / 2.0;
            totalSum -= (x - half);
            heap.insert(half);
            totalSteps++;
        }
        return totalSteps;
    }

    /* simple 1‑based max‑heap on primitive doubles */
    private static class MaxHeap {
        private final double[] a;
        private int size = 0;

        MaxHeap(int cap) { a = new double[cap + 1]; }

        void insert(double v) {
            a[++size] = v;
            siftUp(size);
        }

        double poll() {
            double res = a[1];
            a[1] = a[size--];
            siftDown(1);
            return res;
        }

        private void siftUp(int i) {
            while (i > 1) {
                int p = i >> 1;
                if (a[p] >= a[i]) break;
                swap(p, i);
                i = p;
            }
        }

        private void siftDown(int i) {
            for (;;) {
                int l = i << 1, r = l + 1, largest = i;
                if (l <= size && a[l] > a[largest]) largest = l;
                if (r <= size && a[r] > a[largest]) largest = r;
                if (largest == i) break;
                swap(i, largest);
                i = largest;
            }
        }

        private void swap(int i, int j) {
            double t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
    }

   
}