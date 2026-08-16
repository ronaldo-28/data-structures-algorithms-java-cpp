class Solution {
    public int countValidSubarrays(int[] nums, int x) {
        int n = nums.length;

        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        int answer = 0;

        int[] left = new int[16];
        int[] right = new int[16];
        int[][] frequency = new int[16][10];

        long[] powerOfTen = new long[16];
        powerOfTen[0] = 1;

        for (int p = 1; p < 16; p++) {
            powerOfTen[p] = powerOfTen[p - 1] * 10;
        }

        for (int end = 0; end < n; end++) {
            long currentPrefix = prefix[end + 1];

            int requiredRemainder = (int) ((currentPrefix - x) % 10);

            if (requiredRemainder < 0) {
                requiredRemainder += 10;
            }

            for (int p = 0; p < 16; p++) {
                long place = powerOfTen[p];

                long maximumPrefix =
                    currentPrefix - (long) x * place;

                long minimumPrefix =
                    currentPrefix - (long) (x + 1) * place;

                while (
                    right[p] <= end &&
                    prefix[right[p]] <= maximumPrefix
                ) {
                    int remainder = (int) (prefix[right[p]] % 10);
                    frequency[p][remainder]++;
                    right[p]++;
                }

                while (
                    left[p] < right[p] &&
                    prefix[left[p]] <= minimumPrefix
                ) {
                    int remainder = (int) (prefix[left[p]] % 10);
                    frequency[p][remainder]--;
                    left[p]++;
                }

                answer += frequency[p][requiredRemainder];
            }
        }

        return answer;
    }
}