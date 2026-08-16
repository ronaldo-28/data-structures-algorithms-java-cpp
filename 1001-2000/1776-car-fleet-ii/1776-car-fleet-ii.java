class Solution {

    // 1776 - MonoStack
    // Time: O(N)
    // Space: O(N)
    // Rank: 99.70%
    /* Steps:                           cars = [[1,2],[2,1],[4,3],[7,2]]
                                i = 3, ans[3] = -1, st = [3]
                                i = 2, ans[2] = (7 - 4) / (3 - 2) = 3, st = [2]
                                i = 1, st = [], ans[1] = -1
                                i = 0, ans[0] = 1
                                
                                        cars = [[3,4],[5,4],[6,3],[9,1]]
                                        
                                t = 1,  cars = [[7,4],[9,4],[9,3],[10,1]]
                                        cars = [[7,4],[9,3],[9,3],[10,1]]
                                t = 1.5, cars = [[9,4],[10.5,3],[10.5,3],[10.5,1]]
    */
    public double[] getCollisionTimes(int[][] cars) {
        int n = cars.length, top = -1;
        int[] st = new int[n];
        double[] ans = new double[n];
        for (int i = n - 1; i >= 0; i --) {
            while (top >= 0 && cars[i][1] <= cars[st[top]][1] || top > 0 && chase(cars, i, st[top]) > ans[st[top]]) {
            // while (top >= 0 && (cars[i][1] <= cars[st[top]][1] || ans[st[top]] != -1 && chase(cars, i, st[top]) > ans[st[top]])) {
                top --;
            }
            ans[i] = top < 0 ? -1 : chase(cars, i, st[top]);
            st[++ top] = i;
        }
        return ans;
    }
    
    double chase(int[][] cars, int i, int j) {
        return 1.0 * (cars[j][0] - cars[i][0]) / (cars[i][1] - cars[j][1]);
    }
    
}