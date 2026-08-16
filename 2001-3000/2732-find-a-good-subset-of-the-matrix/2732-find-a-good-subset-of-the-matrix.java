class Solution {
    public List<Integer> goodSubsetofBinaryMatrix(int[][] arr) {
        List<Integer> l = new ArrayList<>();
        int[] freq = new int[33];
        Arrays.fill(freq, -1);
        int m = arr.length, n = arr[0].length, x = 0, y = 1, j = 0;
        for (int i = 0; i < m; i++) {
            for (x = 0, y = 1, j = n - 1; j >= 0; j--) {
                if (arr[i][j] == 1)  x += y;
                y <<= 1;
            }
            y = and(x, freq);
            if (x == 0) {
                l.add(i);
                return l;
            } else if (y != -1) {
                l.add(y);
                l.add(i);
                return l;
            }
            freq[x] = i;
        }
        return l;
    }

    static int and(int current, int[] arr) {
        current = ~current;
        for (int i = 0; i <= 32; i++)
            if (arr[(i & current)] != -1)  return arr[(i & current)];
        return -1;
    }
}