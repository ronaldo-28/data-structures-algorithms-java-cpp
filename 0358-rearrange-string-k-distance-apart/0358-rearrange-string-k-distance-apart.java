class Solution {
    public String rearrangeString(String s, int k) {
        // greedy to arrange max frequency char first
        int[] count = new int[26];
        for (char c : s.toCharArray()) count[c - 'a']++;
        int max = 0, maxCount = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] > max) {
                max = count[i];
                maxCount = 0;
            }
            if (count[i] == max) maxCount++;
        }
        if ((max-1) * (k) + maxCount > s.length()) return "";
        
        
        int[][] combined = new int[26][2];
        for (int i = 0; i < 26; i++) {
            combined[i][0] = i;
            combined[i][1] = count[i];
        }  
        Arrays.sort(combined, (a, b) -> Integer.compare(b[1], a[1]));


        // find way to add to temp
        StringBuilder[] sbs = new StringBuilder[max];
        for (int i = 0; i < max; i++) sbs[i] = new StringBuilder();

        for (int i = 0, r = 0; i < 26 && combined[i][1] > 0; i++) {
            char c = (char)(combined[i][0] + 'a');
            int freq = combined[i][1];
            int rowLimit = freq == max ? max : max-1;
            while (freq-- > 0) {
                sbs[r++].append(c);
                if (r == rowLimit) r = 0;
                // System.out.println(c + " " + freq + " " + rowLimit + " " + r);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < max; i++) sb.append(sbs[i]);
        return sb.toString();
    }
}