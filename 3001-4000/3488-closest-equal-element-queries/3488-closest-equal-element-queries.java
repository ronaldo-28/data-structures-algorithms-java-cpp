class Solution {
    private int circularDistance(int from, int to, int n) {
        if(from <= to)
            return to - from;
        return n - from + to;
    }
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        /**
         0 1 2 3 4 5 6
        [1,3,1,4,1,3,2]
                 i
        {
            1: 4,
            3: 1,
            4: 3,

            
        }
        [dist(0, 2)=2, -1, dist(2, 0)=6-2+1 = 4]

        dist(a,b): a<b => b-a
                   else => n-a+b+1)
         */
         int[] distances = new int[nums.length];
         Map<Integer, int[]> lastSeen = new HashMap<>();
         for(int i=0; i<nums.length; i++) {
            distances[i] = Integer.MAX_VALUE;
            if(lastSeen.containsKey(nums[i])) {
                // calculate distance on the old item
                int lastSeenIdxLeft = lastSeen.get(nums[i])[0];
                int lastSeenIdxRight = lastSeen.get(nums[i])[1];
                distances[lastSeenIdxRight] = Math.min(distances[lastSeenIdxRight], circularDistance(lastSeenIdxRight, i, nums.length));
                distances[lastSeenIdxLeft] = Math.min(distances[lastSeenIdxLeft], circularDistance(i, lastSeenIdxLeft, nums.length));
                distances[i] = Math.min(circularDistance(lastSeenIdxRight, i, nums.length),
                circularDistance(i, lastSeenIdxLeft, nums.length));
                lastSeen.put(nums[i], new int[] {lastSeen.get(nums[i])[0],i});
            } else {
                lastSeen.put(nums[i], new int[] {i,i});
            }
         }
        
        List<Integer> result = new ArrayList<>();
        for(int queryIdx : queries) {
            result.add(distances[queryIdx] == Integer.MAX_VALUE ? -1 : distances[queryIdx]);
        }
        return result;
    }
}