/**
 * // This is ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface ArrayReader {
 *     // Compares the sum of arr[l..r] with the sum of arr[x..y] 
 *     // return 1 if sum(arr[l..r]) > sum(arr[x..y])
 *     // return 0 if sum(arr[l..r]) == sum(arr[x..y])
 *     // return -1 if sum(arr[l..r]) < sum(arr[x..y])
 *     public int compareSub(int l, int r, int x, int y) {}
 *
 *     // Returns the length of the array
 *     public int length() {}
 * }
 */

class Solution {
    public int getIndex(ArrayReader reader) {
        int i=0, j=reader.length()-1, mid, res;
        while (i <= j) {
            mid = i + (j - i)/2;
            if ((j-i)%2 == 1) res = reader.compareSub(i, mid, mid+1, j);
            else res = reader.compareSub(i, mid, mid, j);
            if (res == 0) return mid;
            else if (res == 1) j = mid;
            else i = mid + 1;
        }
        return i+1;
    }
}