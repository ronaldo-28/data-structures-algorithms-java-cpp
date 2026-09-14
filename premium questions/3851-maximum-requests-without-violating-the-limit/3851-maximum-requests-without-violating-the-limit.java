class Solution {
    public int maxRequests(int[][] requests, int k, int window) {
        Map<Integer, List<Integer>> mapUserReq = new HashMap<>();
        for (int[] req : requests) {
            mapUserReq.computeIfAbsent(req[0], x -> new ArrayList<>()).add(req[1]);
        }

        int totalUserReqRemaining = 0;
        for (List<Integer> times : mapUserReq.values()) {
            Collections.sort(times);
            int[] accepted = new int[times.size()];
            int accSize = 0;
            int left = 0;

            for (int time : times) {
                int target = time - window;
                
                while (left < accSize && accepted[left] < target) {
                    left++;
                }

                int count = accSize - left;

                if (count < k) {
                    accepted[accSize++] = time;
                    totalUserReqRemaining++;
                }
            }
        }

        return totalUserReqRemaining;
    }
}