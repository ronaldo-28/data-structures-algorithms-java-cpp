class Solution {

    // how many min needed for "good": 0,1,2,3...
    Integer[][] memo;
    int k;

    // top-down DP with state memoization
    // time: (n^2)
    // space: (n^2)

    public boolean isValidPalindrome(String s, int k) {
        int n = s.length();
        this.k = k;

        if(n <= 1){
            return true;
        }

        memo = new Integer[n][n];
        int result = needed(s, 0, n-1);
        return (result <= k);        
    }

    private int needed(String s, int left, int right){
        if(right <= left){
            return 0;
        }

        if(memo[left][right] != null){
            return memo[left][right];       
        }

        Integer needed = null;
        char chLeft = s.charAt(left);
        char chRight = s.charAt(right);

        if(chLeft == chRight){
            needed = needed(s, left+1, right-1);            
        }else{
            int subLeft = needed(s, left+1, right);
            int subRight = needed(s, left, right-1);            
            needed = 1 + Math.min(subLeft, subRight);
        }

        memo[left][right] = needed;        
        return needed;
    }
}