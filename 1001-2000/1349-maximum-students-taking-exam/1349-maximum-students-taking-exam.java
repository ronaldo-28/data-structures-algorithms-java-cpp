class Solution {
    int m;
    int n;
    int[][] directions = new int[][]{ {0,-1}, {0,1}, {-1,-1}, {1,-1}, {-1,1}, {1,1} };

    public int maxStudents(char[][] seats) {
        this.m = seats.length;
        this.n = seats[0].length;

        // max bipartite matches:
        // the max number of non-cheating students = max independent set
        // max independent set = total vertices - max matching
        // total vertices = total number of usuable seats
        // max matching = max disjoint conflicts between two usuable seats
        int count = 0, total = 0;

        // we will treat each usuable seat as a node, an A <--> B if they can cheat
        // we will use 6 directions instead of 4 since cheating on vs cheated on is the same conflict
        // since each seat can only cheat off of the nearby columns, we will split seats into two sets
        // match[id] = the even seat that is being matched to (cheating on) the odd seat (id)
        int[] match = new int[m * n];
        Arrays.fill(match, -1);

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(seats[i][j] == '#') continue;

                // match only the even seats to the odd seats
                // if we can find a match for the even seats, there is a cheating conflict
                if(j % 2 == 0 && canMatch(seats, match, i, j, new boolean[m * n])) count++;

                total++;
            }
        }
        
        // total seats - forced conflicts = available seats
        return total - count;
    }

    private boolean canMatch(char[][] seats, int[] match, int i, int j, boolean[] seen) {
        for(int[] dir : directions) {
            // find the odd seat (dx, dy) that conflicts with even seat (i, j)
            int dx = i + dir[0];
            int dy = j + dir[1];
            int id = (dx * n) + dy;

            // check if (dx, dy) is in bounds, can be seated, and we have not seen it already
            if(dx >= 0 && dx < m && dy >= 0 && dy < n && !seen[id] && seats[dx][dy] == '.') {
                seen[id] = true;
                int prevId = match[id];

                // if no other seats conflict with (dx, dy) pair (i, j) with it
                // otherwise, try to move the previous pair (i', j') so we can pair (i, j)
                if(prevId == -1 || canMatch(seats, match, prevId / n, prevId % n, seen)) {
                    match[id] = (i * n) + j;
                    return true;
                }
            }
        }

        // seat (i, j) does not conflict
        return false;
    }
}