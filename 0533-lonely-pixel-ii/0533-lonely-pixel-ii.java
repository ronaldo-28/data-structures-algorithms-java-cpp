class Solution {
    public int findBlackPixel(char[][] picture, int target) {
        Map<String, Integer> map = new HashMap<>();
        int m = picture.length, n = picture[0].length;
        int[] col = new int[n];
        for (int i = 0; i < m; i++) {
            int row = 0;
            for (int j = 0; j < n; j++) {
                if (picture[i][j] == 'B') {
                    col[j]++;
                    row++;
                }
            }
            if (row == target) {
                String s = new String(picture[i]);
                map.put(s, map.getOrDefault(s, 0) + 1);
            }
        }
        int res = 0;
        for (String s : map.keySet()) {
            if (map.get(s) != target) continue;
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == 'B' && col[i] == target) {
                    res += target;
                }
            }
        }
        return res;
    }
}