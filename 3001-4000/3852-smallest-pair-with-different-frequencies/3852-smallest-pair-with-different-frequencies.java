class Solution {
    public int[] minDistinctFreqPair(int[] nums) {
        int[]freq = new int[101];
        for (int num : nums) {
            freq[num]++;
        }
        for (int i = 0 ; i<freq.length;i++){
            if (freq[i]==0) continue;
            for (int j=i+1;j<freq.length;j++){
                if (freq[j]==0) continue;
                if (freq[j]!=freq[i]){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{-1,-1};
    }
    }