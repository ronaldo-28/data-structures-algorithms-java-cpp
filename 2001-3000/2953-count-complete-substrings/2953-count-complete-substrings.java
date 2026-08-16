class Solution {
    public int countCompleteSubstrings(String word, int k) {
        char[] sc = word.toCharArray();
        int n = sc.length;
        int totalCompleteSubstrings = 0;
        
        int startBlockIdx = 0;
        
        // Step 1: Divide the string into valid contiguous sub-blocks where adjacent diff <= k
        for (int i = 1; i <= n; i++) {
            if (i == n || Math.abs(sc[i] - sc[i - 1]) > 2) {
                // Process the independent slice from startBlockIdx to i - 1
                totalCompleteSubstrings += countBlockComplete(sc, startBlockIdx, i - 1, k);
                startBlockIdx = i;
            }
        }
        
        return totalCompleteSubstrings;
    }

    // Step 2: Sliding Window across 26 possible window lengths inside a safe block
    private int countBlockComplete(char[] sc, int start, int end, int k) {
        int blockLen = end - start + 1;
        int completeCount = 0;
        
        // Try all combinations of unique character counts from 1 to 26
        for (int uniqueTypes = 1; uniqueTypes <= 26; uniqueTypes++) {
            int windowSize = uniqueTypes * k;
            if (windowSize > blockLen) {
                break; // Window exceeds block capacity, no larger configurations possible
            }
            
            // Fast primitive frequency counter map
            int[] freq = new int[26];
            int validCharMatches = 0;
            
            // Pre-fill the initial sliding window up to size windowSize
            for (int i = 0; i < windowSize; i++) {
                int chIdx = sc[start + i] - 'a';
                if (freq[chIdx] == k) validCharMatches--;
                freq[chIdx]++;
                if (freq[chIdx] == k) validCharMatches++;
            }
            
            // If the initial window meets the condition, increment count
            if (validCharMatches == uniqueTypes) {
                completeCount++;
            }
            
            // Slide the fixed-size window across the rest of the block
            for (int i = windowSize; i < blockLen; i++) {
                // Ingest new character from the right
                int rightCh = sc[start + i] - 'a';
                if (freq[rightCh] == k) validCharMatches--;
                freq[rightCh]++;
                if (freq[rightCh] == k) validCharMatches++;
                
                // Evict old character from the left
                int leftCh = sc[start + i - windowSize] - 'a';
                if (freq[leftCh] == k) validCharMatches--;
                freq[leftCh]--;
                if (freq[leftCh] == k) validCharMatches++;
                
                // Verify window validity
                if (validCharMatches == uniqueTypes) {
                    completeCount++;
                }
            }
        }
        
        return completeCount;
    }
}