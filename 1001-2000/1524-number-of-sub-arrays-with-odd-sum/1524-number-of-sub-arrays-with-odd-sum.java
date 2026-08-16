class Solution {
    public int numOfSubarrays(int[] arr) {
        int sum = 0, odd = 0;
        for(int num : arr) {
            sum ^= num & 1;
            odd += sum;
        }
        return (int)((arr.length - odd + 1L) * odd % 1000000007);
    }
}