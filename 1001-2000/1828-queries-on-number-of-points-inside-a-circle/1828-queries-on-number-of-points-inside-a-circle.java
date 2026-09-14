class Solution {
    private int isInside(int x,int y, int r, int[][] pts){
        int count=0;
        for(int[] n:pts){
        if((x-n[0])*(x-n[0])+(y-n[1])*(y-n[1])<=r*r) count++;
        }
        return count;
    }
    public int[] countPoints(int[][] points, int[][] queries) {
        int arr[]=new int[queries.length];
        int i=0;
        for(int[] n:queries){
            arr[i++]=isInside(n[0],n[1],n[2],points);
        }
        return arr;
    }
}