class Solution {
    static class Counter {
        int cnt = 0;
    }
    public int maxSubarrayLength(int[] nums, int k) {
        int N = nums.length;
        Map<Integer, Counter> map = new HashMap<>();

        int ll = 0;
        int rr = 0;
        int result = 0;
        while (rr < N) {
            int num = nums[rr++];
            Counter counter = map.get(num);
            if ( counter == null ) map.put(num, counter = new Counter());
            if ( counter.cnt < k ) {
                counter.cnt++;
            } else {
                int num2 = 0;
                while ( (num2 = nums[ll++]) != num ) {
                    Counter counter2 = map.get(num2);
                    counter2.cnt--;
                }
            }
            result = Math.max(result, rr - ll);
        }
        return result;
    }
}