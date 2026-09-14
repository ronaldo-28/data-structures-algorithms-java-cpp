class Solution {
    int direction = 1;
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        direction = 1;
        int[][] board = new int[8][8];
        for(int[] queen: queens){
            board[queen[0]][queen[1]] = 1;
        }

        List<List<Integer>> res = new ArrayList<>();
        dfs(board, king[0], king[1], res, king);
        return res;
    }

    private void dfs(int[][] board, int row, int col, List<List<Integer>> res, int[] king){
        if(direction > 8){
            return;
        }

        if(direction == 1){
            row -= 1;
        }
        else if(direction == 2){
            row -= 1;
            col += 1;
        }
        else if(direction == 3){
            col += 1;
        }
        else if(direction == 4){
            row += 1;
            col += 1;
        }
        else if(direction == 5){
            row += 1;
        }
        else if(direction == 6){
            row += 1;
            col -= 1;
        }
        else if(direction == 7){
            col -= 1;
        }
        else if(direction == 8){
            row -= 1;
            col -= 1;
        }

        if(row < 0 || col < 0 || row >= 8 || col >= 8){
            direction++;
            dfs(board, king[0], king[1], res, king);
            return;
        }

        if(board[row][col] == 1){
            res.add(Arrays.asList(row, col));
            direction++;
            dfs(board, king[0], king[1], res, king);
            return;
        }

        dfs(board, row, col, res, king);


    }
}