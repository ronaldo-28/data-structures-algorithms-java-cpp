class Solution {
    public String minimumString(String a, String b, String c) {
        String[] arr = {a, b, c};
        String res = "";
        
        int[][] perms = {
            {0, 1, 2}, {0, 2, 1},
            {1, 0, 2}, {1, 2, 0},
            {2, 0, 1}, {2, 1, 0}
        };
        
        for (int[] p : perms) {
            String s1 = arr[p[0]];
            String s2 = arr[p[1]];
            String s3 = arr[p[2]];
            
            String cur = merge(merge(s1, s2), s3);
            
            if (res.isEmpty() || cur.length() < res.length() || (cur.length() == res.length() && cur.compareTo(res) < 0)) {
                res = cur;
            }
        }
        
        return res;
    }
    
    private String merge(String s1, String s2) {
        if (s1.contains(s2)) return s1;
        if (s2.contains(s1)) return s2;
        
        for (int i = Math.max(0, s1.length() - s2.length()); i < s1.length(); i++) {
            if (s1.regionMatches(i, s2, 0, s1.length() - i)) {
                return s1 + s2.substring(s1.length() - i);
            }
        }
        
        return s1 + s2;
    }
}