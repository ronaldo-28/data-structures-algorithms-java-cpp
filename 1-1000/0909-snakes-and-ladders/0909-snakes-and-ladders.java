class Solution {
    public int snakesAndLadders(int[][] board) {
        // Initialization
        final int n = board.length; // Board size (n x n)
        final int endSquare = n * n; // The target square to reach

        // Flatten the 2D board into a 1D array
        short[] brd = new short[endSquare + 1];
        int brdIdx = 1;

        // Populate the 1D array considering the zigzag pattern
        for (int row = n - 1; row >= 0; row--) {
            for (int col = 0; col < n; col++) 
                brd[brdIdx++] = (short) board[row][col];
            if (--row < 0) break; // Check if the next row exists
            for (int col = n - 1; col >= 0; col--) 
                brd[brdIdx++] = (short) board[row][col];
        }

        // BFS queue and helper arrays
        int bfsQueueLen = Math.min(n * n, 8 * n); // Optimized queue size
        short[] bfsQueue = new short[bfsQueueLen];
        int bfsQueueRead = 0, bfsQueueWrite = 0;
        bfsQueue[bfsQueueWrite++] = 1; // Start BFS from square 1
        byte[] count = new byte[endSquare + 1];
        count[1] = 1; // Starting square has move count 1

        // BFS Traversal
        while (bfsQueueRead != bfsQueueWrite) {
            int currSquare = bfsQueue[bfsQueueRead++];
            bfsQueueRead %= bfsQueueLen; // Handle circular queue wrap-around

            // If the destination can be reached directly
            if (currSquare + 6 >= endSquare) 
                return count[currSquare];

            int maxOpenMove = 0; // Tracks the maximum valid move
            for (int move = 6; move >= 1; move--) { // Process dice rolls (6 to 1)
                int nextSquare = currSquare + move;

                // If the square contains a ladder or snake
                if (brd[nextSquare] >= 0) {
                    nextSquare = brd[nextSquare];
                    if (nextSquare == endSquare) 
                        return count[currSquare];
                } else {
                    // Skip if move is worse than the maximum open move found
                    if (move < maxOpenMove) 
                        continue;
                    maxOpenMove = move;
                }

                // Add valid squares to the BFS queue
                if (count[nextSquare] == 0) { // If not visited
                    count[nextSquare] = (byte) (count[currSquare] + 1);
                    bfsQueue[bfsQueueWrite++] = (short) nextSquare;
                    bfsQueueWrite %= bfsQueueLen; // Handle circular queue wrap-around

                    // Queue overflow check
                    if (bfsQueueWrite == bfsQueueRead) 
                        return 0; // Error: Queue overflow
                }
            }
        }

        return -1; // Return -1 if destination is unreachable
    }
   




        /*Queue<Integer> q = new LinkedList<>();
        int[] visited = new int[n + 1]; //start from 1
        Arrays.fill(visited, 1000000);
        q.offer(1);
        visited[1] = 0;
        while(!q.isEmpty()){
            int square = q.poll();  
            //System.out.println("the square: " + square);
            int squareValue = getBoardValue(board, square);    
            //System.out.println("square value: " + squareValue);
            for(int i = 1; i<=6; i++){ // simulate all the cases of the dice
                System.out.println("Dice: " + i);
                int nextSquare = square + i;   
                int nextValue = getBoardValue(board, nextSquare);
                if(nextValue > 0){
                    //System.out.println("Next Square: " + nextValue);
                    nextSquare = nextValue;
                }
                if(visited[nextSquare] < 1000000){
                    continue;
                }
                if(nextSquare >= n) {
                    return visited[square] + 1; 
                }
                q.offer(nextSquare); 
                visited[nextSquare] = Math.min(visited[nextSquare], visited[square] + 1);

            }                
        }
        return -1;
    }*/

    private int getBoardValue(int[][] board, int num) {
        int n = board.length;
        int r = (num - 1) / n;
        int x = n - 1 - r;
        int y = r % 2 == 0 ? num - 1 - r * n : n + r * n - num;
        //System.out.println("x/y: " + x + "/" + y);
        return board[x][y];
    }
}