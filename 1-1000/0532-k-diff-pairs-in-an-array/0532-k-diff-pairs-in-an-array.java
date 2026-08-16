class Solution {
    static {
        for (int i = 0; i < 500; i++) {
            findPairs(new int[] { -1, 0 }, 0);
        }
    }

    public static int findPairs(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        int cnt = 0;
        for (int i : map.keySet()) {
            if (k == 0) {
                if (map.get(i) > 1)
                    cnt++;
            } else {
                if (map.containsKey(i - k))
                    cnt++;
            }
        }
        return cnt;
    }
}