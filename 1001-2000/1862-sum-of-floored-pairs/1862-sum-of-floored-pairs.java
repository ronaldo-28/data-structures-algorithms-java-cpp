class Solution {
    public int sumOfFlooredPairs(int[] nums) {
    int mod = 1_000_000_007;
    int max = 0;

    for (int num : nums) {
        max = Math.max(max, num);
    }

    int[] freq = new int[max + 1];

    for (int num : nums) {
        freq[num]++;
    }

    // prefix sum
    int[] prefix = new int[max + 1];
    for (int i = 1; i <= max; i++) {
        prefix[i] = prefix[i - 1] + freq[i];
    }

    long result = 0;

    for (int i = 1; i <= max; i++) {
        if (freq[i] == 0) continue;

        for (int j = i; j <= max; j += i) {
            int right = Math.min(j + i - 1, max);

            int count = prefix[right] - prefix[j - 1];

            result += (long) freq[i] * count * (j / i);
        }
    }

    return (int) (result % mod);
}
    
}