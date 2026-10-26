class Solution {
    public int maxCollectedFruits(int[][] fruits) {
        /*top left corner child must go along diag to reach in time
        top right child must always move down rows, but can choose cols when possible
        bottom left will always move right cols, but can choose rows when possible
        
        neither child 2 or 3 should ever go on diagonal except for the end
            - if they go on diagonal, they take at least the same amount of moves as child 1 to reach it
            thus they must stay on the diagonal, collecting no extra fruit for the rest as child 1 will collect all diag fruit
        
        child 2 (top right) can never have row > col,
            -moves child 2 has made = row, meaning moves remaining = n-1-row
            if row > col, then n-1-col > n-1-row, meaning it would take more than the remainign moves to return to col n-1 by the end
        
        child 3 (bottom left) by same logic as child 2 cannot ever have row < col

        since row != col for either as they cannot use diag (with exception of n-1 n-1), this means
            child 1 pos always holds: row = col
            child 2 pos always holds: row < col
            child 3 pos always holds: row > col
        This means all children will be disjoint, i.e. as long as they follow these rules they won't overlap
        so can calculate max fruit for each individual child accourding to rules then add them up, subtracting 2*fruit(n-1, n-1) at end to account for triple last fruit
        */
        int n = fruits.length;
        for(int i = 1; i < n; i++) {
            fruits[i][i] += fruits[i-1][i-1];
            for(int j = i+1; j < n; j++) {
                if(i+j < n-1) continue;
                
                fruits[i][j] += Math.max(j == n-1 ? 0 : fruits[i-1][j+1], i + j == n-1 ? 0 : Math.max(fruits[i-1][j], j == 0 || i + j <= n ? 0 : fruits[i-1][j-1]));
                fruits[j][i] += Math.max(j == n-1 ? 0 : fruits[j+1][i-1], i + j == n-1 ? 0 : Math.max(fruits[j][i-1], j == 0 || i + j <= n ? 0 : fruits[j-1][i-1]));
            }
        }
        return fruits[n-1][n-2] + fruits[n-2][n-1] + fruits[n-1][n-1];
    }
}