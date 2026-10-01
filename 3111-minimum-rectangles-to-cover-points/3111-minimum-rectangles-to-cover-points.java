class Solution {
    public int minRectanglesToCoverPoints(int[][] points, int w) {
        Arrays.sort(points, (a,b) -> a[0]-b[0]);
        int count=0;
        for(int i=0;i<points.length;i++)
        {
            int j=i;
            while((j < points.length) && (points[j][0] - points[i][0] <= w) && (points[i][1] >= 0))
            {
                j++;
            }
            count++;
            i=j-1;
        }
        return count;
    }
}