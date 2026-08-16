class Solution {
    public int[] numberGame(int[] nums) {
         int[] count = new int[101];
        int[] rez = new int[nums.length];
        int idx = 0;

        for (int num : nums) {
            count[num]++;
        }

        for (int num = 1; num < 101; num++) {

            while (count[num] > 0) {

                int first = num;
                count[num]--;

                while (count[num] == 0) {
                    num++;
                }

                int second = num;
                count[num]--;

                rez[idx++] = second;
                rez[idx++] = first;
            }
        }

        return rez;
    }
}