class Solution {
   int[] cap;
  int[] jobs;

  public int minimumTimeRequired(int[] jobs, int k) {
    this.jobs = jobs;
    sort(jobs);
    int n = jobs.length;
    int left = jobs[n - 1];
    int right = jobs[n - 1] * n;
    cap = new int[k];
    while (left < right) {
      int mid = (right + left) >> 1;
      Arrays.fill(cap, mid);
      if (dfs(n - 1, k, mid)) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    return left;
  }

  private boolean dfs(int i, int k, int x) {
    if (i == -1) {
      return true;
    }
    for (int j = 0; j < k; j++) {
      if (cap[j] >= jobs[i]) {
        cap[j] -= jobs[i];
        if (dfs(i - 1, k, x))
          return true;

        cap[j] += jobs[i];
      }
      if (cap[j] == x)
        break;

    }
    return false;
  }
  void sort(int[] nums) {
      for (int i = 1; i < nums.length; i++) {
        int j = i;
        while (j > 0 && nums[j - 1] > nums[j])
          swap(nums, j - 1, j--);
      }
    }

    void swap(int[] n, int i, int j) {
      int t = n[i];
      n[i] = n[j];
      n[j] = t;
    }
}