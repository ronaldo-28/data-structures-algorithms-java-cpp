class Solution {
    public String bestHand(int[] ranks, char[] suits) {
        int []hashr = new int[15] ; // Check Constraints
        int []hashs = new int[5] ; // Check Constraints
        for(int i = 0 ; i < 5 ; i++)
        {
            hashr[ranks[i]] += 1 ;
            hashs[suits[i] - 'a'] += 1 ;
        }
        String ans = "High Card" ;
        for(int i = 0 ; i < 5 ; i++) // Checking same suit
        {
            if(hashs[i] == 5)
            {
                ans = "Flush" ;
				break ;
            }
        }
        for(int i = 0 ; i < 14 ; i++) // Checking same rank
        {
            if(hashr[i] >= 3)
            {
                ans = "Three of a Kind" ;
                return ans ;
            }
            else if(hashr[i] == 2)
            {
                ans = "Pair" ;
            }
        }
        return ans ;
    }
}