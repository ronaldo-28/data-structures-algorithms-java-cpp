class Solution {
    public int findNumberOfLIS(int[] nums) {
        final int len = nums.length;
        final ArrayList<int[]>[] arr = new ArrayList[len];

        int size = 0;
        for (int n : nums) {
            final int insertIndex = insertIndex(arr, n, size);
            final ArrayList<int[]> list;
            final int[] prevPair;
            if (insertIndex == size) {
                list = new ArrayList<>();
                arr[insertIndex] = list;
                prevPair = null;
                size++;
                
            } else {
                list = arr[insertIndex];
                prevPair = list.get(list.size() - 1);
            }

            int count = prevPair == null ? 0 : prevPair[1];
            if (insertIndex == 0) count += 1;
            else {
                final List<int[]> prevList = arr[insertIndex - 1];
                final int prevSize = prevList.size();
                count += prevList.get(prevSize - 1)[1];

                for (int i = prevSize - 1; i >= 0; i--) {
                    final int[] pair = prevList.get(i);
                    if (pair[0] >= n) {
                        count -= pair[1];
                        break;
                    }
                }
            }
            if (prevPair != null && prevPair[0] == n) prevPair[1] = count; 
            else list.add(new int[] {n, count});
        }

        final List<int[]> list = arr[size - 1];
        return list.get(list.size() - 1)[1];
    }

    private static int insertIndex(ArrayList<int[]>[] arr, int n, int size) {
        int left = 0;
        int right = size;
        while (left < right) {
            final int mid = left + (right - left) / 2;
            final int midVal = arr[mid].get(arr[mid].size() - 1)[0];
            if (midVal < n) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}