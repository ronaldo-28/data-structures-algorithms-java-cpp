class Solution {
    public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        int count = 0, n = chargeTimes.length;
        int[] queue = new int[n];
        int right = -1, left = 0;
        long sum = 0;
        for(int i = 0; i < n; i++) {
            sum += runningCosts[i];
            while(right >= left && queue[right] < chargeTimes[i]) right--;
            queue[++right] = chargeTimes[i];
            if(queue[left] + sum + count * sum > budget) {
                sum -= runningCosts[i - count];
                if(queue[left] == chargeTimes[i - count]) left++;
            }else count++;
        }
        return count;
    }
}