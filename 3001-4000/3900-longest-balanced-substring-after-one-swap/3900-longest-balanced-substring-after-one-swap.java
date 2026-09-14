class Solution {
    public static int longestBalanced(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        int bal = n + 2, ans = 0; //bal = ones - zeros, starts at n + 2 and ranges from 2 to 2 * n + 2

        int[] nextIndex = new int[n]; //nextIndex[i] = the next index that has the same balance as index 'i'
        int[] balIndex = new int[2 * n + 4]; //balIndex[bal] = next index that has a balance of 'bal'
        Arrays.fill(balIndex, n + 1);

        //go backwards and populate the 'nextIndex' and 'balIndex' arrays
        for(int i = n - 1; i >= 0; i--) {
            //faster way of incrementing bal if '1', decrementing if '0'
            //it's the same as "if(arr[i] == '1') { bal++; } else { bal--; }"
            bal += (('0' ^ arr[i]) << 1) - 1;

            //update the nextIndex for each index
            nextIndex[i] = balIndex[bal];
            balIndex[bal] = i;
        }

        if(bal == n + 2) return n; //return early if the whole string is balanced

        //we can figure out the # of zeros using 'bal' and 'n', since we know n = ones + zeros
        //bal = n + 2 + (ones - zeros)
        //bal = n + 2 + ((ones + zeros) - 2 * zeros)
        //bal = n + 2 + (n - 2 * zeros)
        //2 * zeros = (2 * n + 2) - bal
        int zeros = (2 * n + 2 - bal) / 2, ones = n - zeros;

        //it's impossible to find a valid substring longer than this
        int maxLength = 2 * Math.min(zeros, ones);

        //for each index 'i', find the largest valid substring ending at 'i'
        for(int i = 1; i <= n && ans < maxLength; i++) {
            bal += (('1' ^ arr[i - 1]) << 1) - 1; //update bal in reverse, cuz we goin from left to right now

            //if theres a valid substring with no swaps needed, update our answer if it's the longest
            if(i - balIndex[bal] > ans) ans = i - balIndex[bal];

            //now check for substrings where we swap a '0' to a '1', changing the balance by 2
            //if balIndex[bal - 2] is invalid, set it to the next index with the same value of 'bal - 2'
            if(balIndex[bal - 2] < i - maxLength) balIndex[bal - 2] = nextIndex[balIndex[bal - 2]];

            //the substring from balIndex[bal - 2] to 'i' is valid, so update the answer if needed
            if(i - balIndex[bal - 2] > ans) ans = i - balIndex[bal - 2];

            //repeat the last 2 steps but with 'bal + 2' now, for a '1' to '0' swap
            if(balIndex[bal + 2] < i - maxLength) balIndex[bal + 2] = nextIndex[balIndex[bal + 2]];
            if(i - balIndex[bal + 2] > ans) ans = i - balIndex[bal + 2];
        }

        return ans;
    }
}