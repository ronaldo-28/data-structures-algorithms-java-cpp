class Solution {
 public int shortestWordDistance(String[] words, String a, String b) {
    	int n = words.length;
        if (a.equals(b)) {
            int prev = -1;
            int res = n;
            for (int i = 0; i < n; i++) {
            	if (words[i].equals(a)) {                    
                    if (prev != -1) {
                    	res = Math.min(res, i-prev);
                    }
                    prev = i;
                }
            }
            return res;
        } else {
        	int ai = -1;
            int bi = -1;
            int res = n;
            for (int i = 0; i < n; i++) {
            	if (words[i].equals(a)) {
                	ai = i;
                    
                    if (bi != -1) {
                    	res = Math.min(res, ai-bi);
                    }
                } else if (words[i].equals(b)) {
                	bi = i;
                    if (ai != -1) {
                    	res = Math.min(res, bi-ai);
                    }
                }
            }
            return res;
        }
    }
}