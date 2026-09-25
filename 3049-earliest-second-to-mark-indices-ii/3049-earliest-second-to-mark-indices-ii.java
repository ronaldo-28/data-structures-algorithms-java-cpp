class Solution {
    public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
        int n = nums.length;
        int m = changeIndices.length;

        if (m < n) return -1;

        long sum = 0;
        for (int x : nums) {
            sum += x;
        }

        int[] first = new int[m];
        Arrays.fill(first, -1);
        boolean[] seen = new boolean[n];

        for (int s = 0; s < m; s++) {
            int idx = changeIndices[s] - 1;
            if (!seen[idx]) {
                seen[idx] = true;
                if (nums[idx] > 0) {
                    first[s] = idx;
                }
            }
        }

        int left = n;
        int right = m;
        int answer = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canFinish(mid, nums, first, sum, n)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }

    private boolean canFinish(int time, int[] nums, int[] first, long sum, int n) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long totalOperationsSaved = 0;
        int freeMarks = 0;

        for (int s = time - 1; s >= 0; s--) {
            if (first[s] != -1) {
                int idx = first[s];
                pq.offer(nums[idx]);
                totalOperationsSaved += nums[idx];

                if (freeMarks == 0) {
                    if (!pq.isEmpty()) {
                        totalOperationsSaved -= pq.poll();
                    }
                    freeMarks++; 
                } else {
                    freeMarks--;
                }
            } else {
                freeMarks++;
            }
        }

        int resetCount = pq.size();
        
        long operationsNeeded = sum + n - totalOperationsSaved + resetCount;

        return operationsNeeded <= time;
    }
}