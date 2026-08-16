class Solution {

    List<List<Integer>> pacificAtlantic(int[][] matrix) {
        return new java.util.AbstractList<List<Integer>>() {
            private List<List<Integer>> res;

            public List<Integer> get(int i) {
                init();
                return res.get(i);
            }

            public int size() {
                init();
                return res.size();
            }

            private void init() {
                if (res==null) {
                    res=solve(matrix);
                }
            }
        };
    }
    private static final int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    public List<List<Integer>> solve(int[][] matrix) {
        
        List<List<Integer>> res=new ArrayList<>();
        if (matrix==null || matrix.length==0) {
            return res;
        }
        int m=matrix.length;
        int n=matrix[0].length;
        char[][] visited=new char[m][n];
        for (int col=0; col<n; col++) {
            dfs(matrix, 0, col, visited, 'P', res);
        }
        for (int row=0; row<m; row++) {
            dfs(matrix, row, 0, visited, 'P', res);
        }      
        for (int col=0; col<n; col++) {
            dfs(matrix, m-1, col, visited, 'A', res);
        }
        for (int row=0; row<m; row++) {
            dfs(matrix, row, n-1, visited, 'A', res);
        }
        return res;
    }
    
    private void dfs(int[][] matrix, int row, int col, char[][] visited, char ch, List<List<Integer>> res) {
        if (visited[row][col]=='P' && ch=='A') {
            res.add(new ArrayList<Integer>() {{
                add(row);
                add(col);
            }});
        }
        visited[row][col]=ch;
        for (int[] dir : directions) {
            int x=row+dir[0];
            int y=col+dir[1];
            if (x>=0 && x<matrix.length && y>=0 && y<matrix[0].length && matrix[row][col]<=matrix[x][y] && visited[x][y]!=ch) {
                dfs(matrix, x, y, visited, ch, res);
            }
        }        
    }
}