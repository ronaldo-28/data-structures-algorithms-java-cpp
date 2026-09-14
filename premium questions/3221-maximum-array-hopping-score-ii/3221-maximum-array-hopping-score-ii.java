class Solution {
  public long maxScore(int[] nums) {
    Deque<Integer> deque = new ArrayDeque<>();
    for (int i = nums.length - 1; i >= 0; --i) {
      if (deque.isEmpty()) {
        deque.push(i);
      } else {
        if (nums[i] > nums[deque.peek()]) {
          deque.push(i);
        }
      }
    }
    long res = 0;
    int prev = 0;
    while(!deque.isEmpty()) {
      int top = deque.pop();
      res += (long)nums[top] * (top - prev);
      prev = top;
    }

    return res;
  }
}