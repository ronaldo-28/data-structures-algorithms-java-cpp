import java.util.ArrayDeque;

class Solution {
    private static final int MAX_VALUE = 50_000;
    private static final boolean[] isComposite = new boolean[MAX_VALUE + 1];

    static {
        isComposite[0] = true;
        isComposite[1] = true;

        for (int prime = 2; prime <= MAX_VALUE / prime; prime++) {
            if (isComposite[prime]) {
                continue;
            }

            // Smaller multiples were already marked by smaller primes.
            for (int multiple = prime * prime; multiple <= MAX_VALUE; multiple += prime) {
                isComposite[multiple] = true;
            }
        }
    }

    public int primeSubarray(int[] nums, int k) {
        // Store indices of prime occurrences only.
        ArrayDeque<Integer> maxDeque = new ArrayDeque<>();
        ArrayDeque<Integer> minDeque = new ArrayDeque<>();

        int left = 0;
        int count = 0;
        int lastPrime = -1;
        int secondLastPrime = -1;

        for (int right = 0; right < nums.length; right++) {
            int value = nums[right];

            if (!isComposite[value]) {
                secondLastPrime = lastPrime;
                lastPrime = right;

                // Decreasing values: front holds the maximum prime.
                while (!maxDeque.isEmpty() && nums[maxDeque.peekLast()] < value) {
                    maxDeque.pollLast();
                }
                maxDeque.offerLast(right);

                // Increasing values: front holds the minimum prime.
                while (!minDeque.isEmpty() && nums[minDeque.peekLast()] > value) {
                    minDeque.pollLast();
                }
                minDeque.offerLast(right);
            }

            // Shrink until the prime-value range satisfies the condition.
            while (!maxDeque.isEmpty() && !minDeque.isEmpty()
                    && nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > k) {

                // Remove the departing index from BOTH deques if present.
                if (maxDeque.peekFirst() > minDeque.peekFirst()) {
                    left = minDeque.pollFirst() + 1;
                } else if (maxDeque.peekFirst() < minDeque.peekFirst()) {
                    left = maxDeque.pollFirst() + 1;
                } else {
                    left = maxDeque.pollFirst() + 1;
                    minDeque.pollFirst();
                }
            }

            // Starts in [left, secondLastPrime] include at least two primes.
            // If secondLastPrime < left, no valid start exists.
            if (secondLastPrime >= left) {
                count += secondLastPrime - left + 1;
            }
        }

        return count;
    }
}