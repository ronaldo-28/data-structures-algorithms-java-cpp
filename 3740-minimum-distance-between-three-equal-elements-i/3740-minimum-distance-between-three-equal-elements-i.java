class Solution {
  public int minimumDistance(int[] nums) {
    int n = nums.length;
    int[] map = new int[3 * n + 1];
    int result = Integer.MAX_VALUE;

    for (int i = 0; i < n; i++) {
      result = Math.min(result, distance(map, i + 1, nums[i]));
    }

    return result == Integer.MAX_VALUE ? -1 : result;
  }

  private int distance(int[] map, int k, int num) {
    int index = (num - 1) * 3 + 1;
    if (map[index + 2] != 0) {
      map[index] = map[index + 1];
      map[index + 1] = map[index + 2];
      map[index + 2] = k;
      return calculateDistance(map, index);
    } else {
      for (int i = 0; i < 3; i++) {
        if (map[index + i] == 0) {
          map[index + i] = k;
          break;
        }
      }
      if (map[index + 2] != 0) {
        return calculateDistance(map, index);
      }
    }
    return Integer.MAX_VALUE;
  }

  private int calculateDistance(int[] map, int index) {
    return (map[index + 2] << 1) - (map[index] << 1);
  }
}