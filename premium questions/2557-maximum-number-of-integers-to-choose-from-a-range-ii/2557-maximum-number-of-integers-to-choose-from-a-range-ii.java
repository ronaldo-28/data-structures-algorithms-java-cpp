class Solution {
    public int maxCount(int[] banned, int n, long maxSum) {
        // step 1, find the initial sum
        int k = Math.min(n, (int)Math.floor((-1 + Math.sqrt(1 + 8 * maxSum)) / 2));
        long currentSum = (long)k * (k + 1) / 2;
        int count = k;

        // step 2, remove the banned numbers from the sum
        HashSet<Integer> bannedSet = new HashSet<>();
        for (int b : banned) {
            if (!bannedSet.contains(b)) {
                bannedSet.add(b);

                if (b <= k) {
                    currentSum -= b;
                    count -= 1;
                }
            }
        }

        // step 3, add non banned numbers to the sum until
        // we exceed maxSum, or run out of numbers
        for (int i = k + 1; i <= n; i++) {
            if (currentSum + i > maxSum) {
                return count;
            }

            if (!bannedSet.contains(i)) {
                currentSum += i;
                count += 1;
            }
        }

        return count;
    }
}