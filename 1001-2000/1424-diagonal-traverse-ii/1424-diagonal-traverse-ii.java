class Solution {
  public int[] findDiagonalOrder(List<List<Integer>> nums) {
    int max = 0;
    int length = 0;
    List<Integer>[] map = new ArrayList[100001];
    for (int i = 0; i < nums.size(); i++) {
      List<Integer> list = nums.get(i);
      int size = list.size();
      length += size;
      for (int j = 0; j < size; j++) {
        int sum = i + j;
        if (map[sum] == null) {
          map[sum] = new ArrayList<>();
        }
        map[sum].add(list.get(j));
        max = Math.max(max, sum);
      }
    }
    int[] result = new int[length];
    int index = 0;
    for (int i = 0; i <= max; i++) {
      List<Integer> curr = map[i];
      for (int j = curr.size() - 1; j >= 0; j--) {
        result[index++] = curr.get(j);
      }
    }
    return result;
  }
}