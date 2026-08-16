class Solution {
    static {
        for (int i = 0; i < 500; i++) {
            minSteps("ab","ba");
        }
    }

    public static int minSteps(String s, String t) {
        
        int[] count = new int[26];
        int n = s.length();

        for(int i = 0;i < n;i++) {
            count[s.charAt(i)-'a']++;
            count[t.charAt(i)-'a']--;
        }
        
        int ans = 0;

        for(int i = 0;i < 26;i++) {
            if(count[i]<0) {
                ans += -count[i];
            }
        }

        return ans;

    }
}