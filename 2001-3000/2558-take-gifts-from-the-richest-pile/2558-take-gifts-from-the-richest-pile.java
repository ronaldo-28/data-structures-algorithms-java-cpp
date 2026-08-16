class Solution {
  public long pickGifts(int[] gifts, int k) {
    int n = gifts.length;
    for (int i = n / 2 - 1; i >= 0; i--) {
      heapifyDown(gifts, n, i);
    }
    for (int i = 0; i < k; i++) {
      if (gifts[0] == 1) break;
      gifts[0] = (int) Math.sqrt(gifts[0]);
      heapifyDown(gifts, n, 0);
    }
    long ans = 0;
    for (int gift : gifts) {
      ans += gift;
    }
    return ans;
  }

  private void heapifyDown(int[] arr, int n, int i) {
    int maxIdx = i;
    while (true) {
      int left = 2 * i + 1;
      int right = 2 * i + 2;
      if (left < n && arr[left] > arr[maxIdx]) {
        maxIdx = left;
      }
      if (right < n && arr[right] > arr[maxIdx]) {
        maxIdx = right;
      }
      if (maxIdx == i) {
        break;
      }
      int temp = arr[i];
      arr[i] = arr[maxIdx];
      arr[maxIdx] = temp;
      i = maxIdx;
    }
  }
}