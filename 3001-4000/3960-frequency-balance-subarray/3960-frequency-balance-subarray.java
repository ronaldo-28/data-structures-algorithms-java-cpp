class Solution {
    public int getLength(int[] nums) {
        int n = nums.length, idx = 0;
        Map<Integer,Integer> map = new HashMap<>();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            int v = 0;
            if (map.containsKey(nums[i])) {
                v = map.get(nums[i]);
            } else {
                v = idx++;
                map.put(nums[i], v);
            }
            a[i] = v;
        }
        int res = 1;
        for (int l = 0; l < n; l++) {
            int[] cnt = new int[idx], freq = new int[n + 5];
            int distinct = 0, min = 0, max = 0;
            for (int r = l; r < n; r++) {
                int x = a[r], c = cnt[x];
                if (c == 0) {
                    cnt[x] = 1;
                    distinct++;
                    freq[1]++;
                    min = 1;
                    if (max == 0) max = 1;
                } else {
                    cnt[x] = c + 1;
                    freq[c]--;
                    freq[c + 1]++;
                    if (c + 1 > max) max = c + 1;
                    if (c == min && freq[c] == 0) {
                        while (min <= max && freq[min] == 0) min++;
                    }
                }
                int len = r - l + 1;
                if (len > res && (distinct == 1 || (min * 2 == max && distinct == freq[min] + freq[max]))) {
                    res = len;
                }
            }
        }
        return res;
    }
}