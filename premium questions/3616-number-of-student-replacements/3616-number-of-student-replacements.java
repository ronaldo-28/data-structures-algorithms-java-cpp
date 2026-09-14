class Solution {
    public int totalReplacements(int[] ranks) {
        int betterRank=ranks[0];
        int totalReplacement=0;
        for(int i=1;i<ranks.length;i++)
        {
            if(ranks[i]<betterRank)
            {
                totalReplacement++;
                betterRank=ranks[i];
            }
        }

        return totalReplacement;
    }
}