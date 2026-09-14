class Solution {
    public long findMaximumElegance(int[][] arr, int k) {
        int n = arr.length;
        long s = 0, ms = 0, distinct = 0;
        int[] freq = new int[n + 1];
        Arrays.sort(arr, (a, b) -> b[0] - a[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < k; i++) {
            s += arr[i][0];
            if (freq[arr[i][1]] == 0)
                distinct++;
            if (freq[arr[i][1]] > 0)
                pq.add(new int[] { arr[i][0], arr[i][1] });
            freq[arr[i][1]]++;
            ms = Math.max(s + distinct * distinct, ms);

        }
        for (int i = k; i < n; i++) {
            if (freq[arr[i][1]] == 0) {
                if (!pq.isEmpty()) {
                    s -= pq.poll()[0];
                    s += arr[i][0];
                    distinct++;
                    freq[arr[i][1]]--;
                    ms = Math.max(s + (distinct) * (distinct), ms);
                }
            }
        }
        return ms;
    }
}