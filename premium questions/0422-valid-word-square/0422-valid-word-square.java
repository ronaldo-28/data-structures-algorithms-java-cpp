class Solution {
    public boolean validWordSquare(List<String> words) {
        int n = words.size();
        if (n != words.get(0).length())  return false;
        byte[][] grid = new byte[n][n];
        byte[] gridRow;
        for (int row = 0; row < n; row++) {
            String s = words.get(row);
            if (s.length() > n)  return false;
            gridRow = grid[row];
            s.getBytes(0, s.length(), gridRow, 0);
            for (int col = row; col >= 0; col--)
                if (gridRow[col] != grid[col][row])  return false;
        }
        return true;
    }
}