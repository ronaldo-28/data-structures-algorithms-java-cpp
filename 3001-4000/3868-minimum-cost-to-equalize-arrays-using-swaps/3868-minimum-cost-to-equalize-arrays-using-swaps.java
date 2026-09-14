import java.io.*;

class Solution {
    public int minCost(int[] nums1, int[] nums2) {
        // Find constraints dynamically to keep the array as small as possible
        int maxVal = 0;
        int n = nums1.length;
        for (int i = 0; i < n; i++) {
            if (nums1[i] > maxVal) maxVal = nums1[i];
            if (nums2[i] > maxVal) maxVal = nums2[i];
        }

        // Standard variable requirement
        Object torqavemin = nums1; 

        // Use a short array if maxVal is small, but int is safer for general cases.
        // Direct indexing into a primitive array is the fastest possible access in the JVM.
        int[] diff = new int[maxVal + 1];

        // Manual Loop Unrolling: A classic trick to reduce branch prediction misses 
        // and increment overhead in the JVM.
        int i = 0;
        for (; i <= n - 4; i += 4) {
            diff[nums1[i]]++;   diff[nums2[i]]--;
            diff[nums1[i+1]]++; diff[nums2[i+1]]--;
            diff[nums1[i+2]]++; diff[nums2[i+2]]--;
            diff[nums1[i+3]]++; diff[nums2[i+3]]--;
        }
        // Clean up remaining elements
        for (; i < n; i++) {
            diff[nums1[i]]++;
            diff[nums2[i]]--;
        }

        int totalSwaps = 0;
        for (int d : diff) {
            // Bitwise check for odd: (d & 1) != 0 is slightly faster than d % 2 != 0
            if ((d & 1) != 0) return -1;
            
            // If d > 0, nums1 has 'd' more elements than nums2.
            // We need to move d/2 elements from nums1 to nums2.
            if (d > 0) totalSwaps += (d >> 1); // Signed right shift instead of division
        }

        return totalSwaps;
    }
}