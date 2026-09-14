class Solution {
    public int totalStrength(int[] strength) {
        int n = strength.length;
        int[] stack = new int[n + 1];
        int[] prefix = new int[n + 2];
        stack[0] = -1;
        int index = 0;
        int sum = 0;
        int val = 0;
        for(int i = 0; i < n; i++) {
            val = (val + strength[i]) % 1000000007;
            prefix[i + 1] = (prefix[i] + val) % 1000000007;
            while(index > 0 && strength[stack[index]] > strength[i]) {
                int top = stack[index--];
                int prev = stack[index];
                long left = prev < 0 ? prefix[top] : prefix[top] - prefix[prev], right = prefix[i] - prefix[top];
                sum = (int)(sum + (right * (top - prev) - left * (i - top)) % 1000000007 * strength[top] % 1000000007) % 1000000007;
            }
            stack[++index] = i;
        }
        while(index > 0) {
            int top = stack[index--];
            int prev = stack[index];
            long left = prev < 0 ? prefix[top] : prefix[top] - prefix[prev], right = prefix[n] - prefix[top];
            sum = (int)(sum + (right * (top - prev) - left * (n - top)) % 1000000007 * strength[top] % 1000000007) % 1000000007;
        }
        return (int)((sum + 1000000007) % 1000000007);
    }
}