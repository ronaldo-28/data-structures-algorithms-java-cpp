/*
 * // This is the custom function interface.
 * // You should not implement it, or speculate about its implementation
 * class CustomFunction {
 *     // Returns f(x, y) for any given positive integers x and y.
 *     // Note that f(x, y) is increasing with respect to both x and y.
 *     // i.e. f(x, y) < f(x + 1, y), f(x, y) < f(x, y + 1)
 *     public int f(int x, int y);
 * };
 */

class Solution {
    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
        List<List<Integer>> result = new ArrayList<>();
        int x = 1, y = z;
        while(x <= z && y >= 1) {
            int ans = customfunction.f(x, y);
            if(ans == z) {
                result.add(Arrays.asList(x, y));
                x++;
                y--;
            }
            else if(ans < z) x++;
            else y--;
        }
        return result;
    }
}