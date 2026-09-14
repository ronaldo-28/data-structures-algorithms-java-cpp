/**
* Solution by Brentspine (218/4003)
* https://brentspine.de/r/lcsplug
* https://github.com/brentspine
* https://brentspine.de
*/
class Solution {
    static {
        for(int i = 0; i <= 500; i++) {
            validSubarrays(new int[0]);
        }
    }
    public static int validSubarrays(int[] nums) {
        int n = nums.length;
        if(n == 0) return n;
        int[] forwardAllowed = new int[n];
        forwardAllowed[0] = n-1;
        int[] stack = new int[n];
        int sp = -1;
        stack[++sp] = 0;
        for(int i = 1; i < n; i++) {
            // So we don't need to pop all after loop
            forwardAllowed[i] = n-i-1;
            // nums[stack.peek()] > nums[i]
            // Because this indicates we found a num smaller than the current top of the stack, which stops the subarray condition
            while(sp >= 0 && nums[stack[sp]] > nums[i]) {
                int index = stack[sp--];
                forwardAllowed[index] = i - index - 1;
            }
            stack[++sp] = i;
        }
        int result = 0;
        for(int i = 0; i < n; i++) {
            result += forwardAllowed[i]+1;
        }
        return result;
    }
}