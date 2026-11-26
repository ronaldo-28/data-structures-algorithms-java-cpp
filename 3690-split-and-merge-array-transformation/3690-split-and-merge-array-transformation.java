class Solution {
  public int minSplitMerge(int[] nums1, int[] nums2) {
    int n = nums1.length;
    int[] sorted = nums1.clone();
    java.util.Arrays.sort(sorted);
    int[] a = new int[n], b = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = java.util.Arrays.binarySearch(sorted, nums1[i]);
      b[i] = java.util.Arrays.binarySearch(sorted, nums2[i]);
    }
    // equal values share the first index found by binary search only when duplicates are adjacent;
    // normalise
    for (int i = 0; i < n; i++) {
      while (a[i] > 0 && sorted[a[i] - 1] == sorted[a[i]]) a[i]--;
      while (b[i] > 0 && sorted[b[i] - 1] == sorted[b[i]]) b[i]--;
    }
    long goal = encode(b), startKey = encode(a);
    if (goal == startKey) return 0;
    java.util.Map<Long, Integer> dist = new java.util.HashMap<>();
    java.util.ArrayDeque<int[]> queue = new java.util.ArrayDeque<>();
    dist.put(startKey, 0);
    queue.add(a);
    while (!queue.isEmpty()) {
      int[] cur = queue.poll();
      int d = dist.get(encode(cur));
      for (int l = 0; l < n; l++) {
        for (int r = l; r < n; r++) {
          int[] rest = new int[n - (r - l + 1)];
          int p = 0;
          for (int i = 0; i < n; i++) if (i < l || i > r) rest[p++] = cur[i];
          for (int pos = 0; pos <= rest.length; pos++) {
            int[] next = new int[n];
            int q = 0;
            for (int i = 0; i < pos; i++) next[q++] = rest[i];
            for (int i = l; i <= r; i++) next[q++] = cur[i];
            for (int i = pos; i < rest.length; i++) next[q++] = rest[i];
            long key = encode(next);
            if (dist.containsKey(key)) continue;
            if (key == goal) return d + 1;
            dist.put(key, d + 1);
            queue.add(next);
          }
        }
      }
    }
    return -1;
  }

  private static long encode(int[] arr) {
    long key = 0;
    for (int v : arr) key = key * 7 + v;
    return key;
  }
}