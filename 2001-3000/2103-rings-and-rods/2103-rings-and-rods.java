class Solution {
    public int countPoints(String rings) {
        int[][] arr = new int[10][3];

        for(int i=0; i<rings.length(); i+=2){
            char ch = rings.charAt(i);
            int ind = rings.charAt(i+1)-'0';

            if(ch=='R')
                arr[ind][0] = 1;
            else if(ch=='G')
                arr[ind][1] = 1;
            else
                arr[ind][2] = 1;
        }

        int count = 0;
        for(int[] rod : arr)
            if(rod[0]==1 && rod[1]==1 && rod[2]==1)
                count++;

        return count;
    }
}