class Solution {
    public boolean matchReplacement(String ss, String subb, char[][] mappings) {
        char[] s = ss.toCharArray(), sub = subb.toCharArray();
        boolean[][] match = new boolean[123][123];
        for(int i = 48; i < 123; i++) match[i][i] = true;
        for(char[] x : mappings) match[x[0]][x[1]] = true;
        for(int i = 0; i <= s.length - sub.length; i++) {
            boolean flag = true;
            for(int j = 0; j < sub.length; j++) {
                if(!match[sub[j]][s[i + j]]) {
                    flag = false;
                    break;
                }
            }
            if(flag) return true;
        }
        return false;
    }
}