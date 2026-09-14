class Solution {
    //time complexity:O(mn) space complexity:O(mn)
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int [] [] grid= new int[m][n];
        //0 unshot, 1 guard shot throught, 2 guard, 3 wall
        for(int [] guard: guards){
            grid[guard[0]][guard[1]]=2;
        }
        for(int [] wall: walls){
            grid[wall[0]][wall[1]]=3;
        }
        for(int []guard: guards){
            int currR=guard[0]+1;
            int currC=guard[1];
            while(currR<m){
                if(grid[currR][currC]>1) break;
                grid[currR][currC]=1;
                currR++;
            }
            currR=guard[0]-1;
            while(currR>=0){
                if(grid[currR][currC]>1) break;
                grid[currR][currC]=1;
                currR--;
            }
            currR=guard[0];
            currC=guard[1]+1;
            while(currC<n){
                if(grid[currR][currC]>1) break;
                grid[currR][currC]=1;
                currC++;
            }
            currC=guard[1]-1;
            while(currC>=0){
                if(grid[currR][currC]>1) break;
                grid[currR][currC]=1;
                currC--;
            }
        }
        int count=0;
        for(int [] arr: grid){
            for(int num: arr){
                if(num==0) count++;
            }
        }
        return count;
        
    }
}
/*
G W G W _
_ _ G _ G

2 3 2 3 1
1 1 2 1 2
*/