/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    private int[][] cache;
    public int findCelebrity(int n) {
        this.cache = new int[n][n];
        int candinate = 0;
        for(int i=1; i<n; i++) {
            if(knows(candinate, i)) {
                candinate = i;
            }
        }
        for(int i=0; i<n; i++) {
            if(candinate == i) {
                continue;
            }
            if(!knows(i, candinate) || knows(candinate, i)) {
                return -1;
            }
        }
        return candinate;
    }

    boolean knows(int a, int b) {
        if(cache[a][b] == 0) {
            if(super.knows(a, b)) {
                cache[a][b] = 1;
            } else {
                cache[a][b] = -1;
            }
        }
        return cache[a][b] > 0;
    }
}