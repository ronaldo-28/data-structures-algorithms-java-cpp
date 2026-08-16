class Solution {
    public boolean checkValid(int[][] matrix) {
        // boolean value to know if to check row or col
        // understandle in below recursion where we redirect the    recurion to check for row if the boolean os true and vice verse for column
        return check(matrix,0,true) && check(matrix,0,false);
    }
    public static boolean check(int[][] matrix, int row, boolean way){
        if(row == matrix.length) return true;
        int sum = 0;
        if(way){ // to check row or col (way == true == rowchecking)
            int integer = matrix[row][0];
            for(int i = 0; i < matrix.length; i++){
                sum+= matrix[row][i];
                if(i != 0 && matrix[row][i] == integer) return false;
            }
        }
        else{ // way == false == colchecking
            for(int i = 0; i < matrix.length; i++) sum+= matrix[i][row];
        }
        // 1 to n numbers sum formula as given above in image
        if(sum !=  (matrix.length * (matrix.length + 1)) / 2) return false;
        return check(matrix,row+1,way);
    }
}