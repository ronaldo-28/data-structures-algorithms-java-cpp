class Solution {
    public long fixedRatio(String s, int num1, int num2) {
    
        Map<Long, Integer> mapToTrackCountOfSumEncounted = new HashMap<>();

        // base case : 0 * num2 - 0 * num1 = 0. We have encountered one sum = 0. 
        mapToTrackCountOfSumEncounted.put(0L, 1);


        Long sum = 0L;
        Long ans = 0L;

        for (char c : s.toCharArray()) {
            if (c == '0')
                sum = sum + num2;
            else 
                sum = sum - num1;
            int howManyTimesSumIsEncountered = mapToTrackCountOfSumEncounted.getOrDefault(sum, 0);  
            mapToTrackCountOfSumEncounted.put(sum, 1 + howManyTimesSumIsEncountered);    
            ans = ans + howManyTimesSumIsEncountered;       
        }
        return ans;

    }
}