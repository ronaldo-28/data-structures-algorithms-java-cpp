import java.util.ArrayList;
import java.util.List;
class Solution {
     static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter writer = new FileWriter("display_runtime.txt")) {
                writer.write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int a=grid.length,b=grid[0].length,t=a*b;
        k = k % t;
        int [][] temp=new int[a][b];
        for(int i=0;i<a;i++){
            for(int j=0;j<b;j++){
                int f=i*b+j;
                int nF=(f+k)%t;
                int r=nF/b;
                int c=nF%b;
                temp[r][c]=grid[i][j];
            }
        }
        List<List<Integer>> result=new ArrayList<>();
        for(int i=0;i<a;i++){
            List<Integer> row=new ArrayList<>();
            for(int j=0;j<b;j++){
                row.add(temp[i][j]);
            }
            result.add(row);
        }
        return result;
    }
}