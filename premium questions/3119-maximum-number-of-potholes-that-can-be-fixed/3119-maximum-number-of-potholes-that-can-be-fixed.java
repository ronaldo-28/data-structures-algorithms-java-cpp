class Solution {
    public int maxPotholes(String road, int budget) {
        int n = road.length();
        int[] count = new int[n + 1];

        int consecutive = 0;
        for(int i = 0; i < n; i++) {
            if(road.charAt(i) == 'x') {
                consecutive++;
                continue;
            }

            if(consecutive > 0) {
                count[consecutive]++;
                consecutive = 0;
            }
        }
        if(consecutive > 0) {
            count[consecutive]++;
        }

        int answer = 0;

        for(int len = n; len > 0 && budget > 0; len--) {
            if(count[len] == 0) continue;

            int cost = len + 1;

            if(budget >= cost) {
                int maxCount = Math.min(count[len], budget / cost);
                answer += len * maxCount;
                budget -= cost * maxCount;
                count[len] -= maxCount;
            } 

            if(count[len] > 0 && budget > 0) {
                answer += budget - 1;
                budget = 0;
            }
        }

        return answer;
        
    }
}