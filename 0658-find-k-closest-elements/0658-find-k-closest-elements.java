class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int first = bsearch(arr, x, k);
        return new java.util.AbstractList<>() {
            @Override
            public Integer get(int i) {
                return arr[first + i];
            }
            @Override 
            public int size() {
                return k;
            }
        };
    }
    private int bsearch(int[] arr, int x, int k) {
        int l = 0, r = arr.length - k;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (x - arr[m] > arr[m + k] - x) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }
}
// [1,3,5,6,7]