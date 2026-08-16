class Solution {
    public int specialArray(int[] nums) {
        int n = nums.length;
    int[] freq = new int[n + 1];

    for (int num : nums) {
        if (num >= n) freq[n]++;
        else freq[num]++;
    }

    int count = 0;
    for (int x = n; x >= 0; x--) {
        count += freq[x];
        if (count == x) return x;
    }
    return -1;
    }
}