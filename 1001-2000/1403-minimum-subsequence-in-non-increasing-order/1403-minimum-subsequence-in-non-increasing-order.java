class Solution {
    public List<Integer> minSubsequence(int[] nums) {
        int[] freq = new int[101];
        List<Integer> list = new ArrayList<>();
        int totalSum = 0;
        int subSum = 0;

        for (int num : nums) {
            totalSum += num;
            freq[num]++;
        }

        int half = totalSum / 2;

        for (int i = 100; i >= 1 && subSum <= half; i--) {
            int count = freq[i];

            while (count-- > 0 && subSum <= half) {
                list.add(i);
                subSum += i;
            }
        }

        return list;
    }
}