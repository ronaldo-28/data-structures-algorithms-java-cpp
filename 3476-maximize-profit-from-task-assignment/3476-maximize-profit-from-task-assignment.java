class Solution {
    // time = O(nlogn), space = O(n)
    public long maxProfit(int[] workers, int[][] tasks) {
        HashMap<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int[] t : tasks) {
            map.putIfAbsent(t[0], new PriorityQueue<>((o1, o2) -> o2 - o1));
            map.get(t[0]).offer(t[1]);
        }

        long res = 0;
        for (int x : workers) {
            if (!map.containsKey(x)) continue;
            res += map.get(x).poll();
            if (map.get(x).size() == 0) map.remove(x);
        }
        if (map.size() > 0) {
            int mx = 0;
            for (int x : map.keySet()) {
                mx = Math.max(mx, map.get(x).peek());
            }
            res += mx;
        }
        return res;
    }
}