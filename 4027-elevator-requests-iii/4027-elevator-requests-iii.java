class Solution {
    public long elevatorRequests(int n, int start, int[][] requests) {
        int m = requests.length;
        return solve(start, 0L, 0, requests);
    }
    private long solve(int currFloor, long currTime, int fulfilledMask, int[][] requests) {
        int m = requests.length;
        if (fulfilledMask == (1 << m) - 1) {
            return currTime;
        }
        int minFloor = Integer.MAX_VALUE;
        int maxFloor = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            if ((fulfilledMask & (1 << i)) == 0) {
                minFloor = Math.min(minFloor, requests[i][1]);
                maxFloor = Math.max(maxFloor, requests[i][1]);
            }
        }
        long res = Long.MAX_VALUE;
        int[] targets = (minFloor == maxFloor) ? new int[]{minFloor} : new int[]{minFloor, maxFloor};
        for (int target : targets) {
            int nextMask = fulfilledMask;
            long reachTargetTime = currTime + Math.abs(target - currFloor);
            long waitTargetTime = reachTargetTime;
            for (int i = 0; i < m; i++) {
                if ((fulfilledMask & (1 << i)) == 0) {
                    int f = requests[i][1];
                    long arr = requests[i][0];
                    if ((currFloor <= f && f <= target) || (target <= f && f <= currFloor)) {
                        long passTime = currTime + Math.abs(f - currFloor);
                        if (passTime >= arr) {
                            nextMask |= (1 << i);
                        } 
                        else if (f == target) {
                            waitTargetTime = Math.max(waitTargetTime, arr);
                            nextMask |= (1 << i);
                        }
                    }
                }
            }
            res = Math.min(res, solve(target, waitTargetTime, nextMask, requests));
        }
        return res;
    }
}