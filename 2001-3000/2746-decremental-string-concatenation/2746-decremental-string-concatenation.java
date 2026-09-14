class Solution {
    Integer[][][] dp;
    private int solve(int i, int n, int ps, int pe, String[] words){
        // strategy is we have to just compare first and last
        // taking whole string make no sense
        
		
		if(i == n)
			return 0;
			
        // if present in dp
        if(dp[i][ps][pe] != null)
            return dp[i][ps][pe];
        
        
        String cur = words[i];      

        int cs = cur.charAt(0)-'a';
        int ce = cur.charAt(cur.length()-1)-'a';
        
        // taking current word length two times for 2 next calls
        int len1 = cur.length();
        int len2 = cur.length();
        
        
        // compare middle ones
        if(pe == cs){
            len1--;     // if mid same del current words starting basically decrease length of current
        }

        if(ce == ps){
            len2--;     // if mid same del current words ending basically decrease length of current
        }
        
        
        // send two word    
        int opt1 = solve(i+1, n, ps, ce, words)+len1;       // in this for next call prev+cur so ps is starting ce is ending
        int opt2 = solve(i+1, n, cs, pe, words)+len2;       // cur+prev so cs is starting and pe is ending
        
        return dp[i][ps][pe] = Math.min(opt1, opt2);
    }
    
    public int minimizeConcatenatedLength(String[] words) {
        
        int n = words.length;
        if(n == 1)
            return words[0].length();
        dp = new Integer[n+1][26][26];
        
        int ps = (int)(words[0].charAt(0)-'a');
        int pe = (int)(words[0].charAt(words[0].length()-1)-'a');
        int len = words[0].length();
        
        return solve(1, n, ps, pe, words)+len;
    }
}