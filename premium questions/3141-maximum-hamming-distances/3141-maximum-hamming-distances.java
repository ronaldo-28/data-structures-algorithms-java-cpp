import java.util.*;

class Solution {
    public int[] maxHammingDistances(int[] nums, int m) {
        int maxVal = 1 << m;
        int mask = maxVal - 1;
        
        // dist[i] stores the minimum distance from value i to ANY element present in nums
        int[] dist = new int[maxVal];
        Arrays.fill(dist, -1);

        Queue<Integer> queue = new ArrayDeque<>();

        // Multi-Source BFS Initialization:
        // Push all array elements into the queue as distance 0 sources
        for (int x : nums) {
            if (dist[x] == -1) {
                dist[x] = 0;
                queue.offer(x);
            }
        }

        // Multi-Source BFS over hypercube graph
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            
            // Try flipping each of the m bit positions to visit neighbors
            for (int k = 0; k < m; k++) {
                int neighbor = curr ^ (1 << k);
                if (dist[neighbor] == -1) {
                    dist[neighbor] = dist[curr] + 1;
                    queue.offer(neighbor);
                }
            }
        }

        // Calculate maximum hamming distance for each original element
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int complement = mask ^ nums[i]; // Bitwise complement (~nums[i])
            ans[i] = m - dist[complement];
        }

        return ans;
    }
}