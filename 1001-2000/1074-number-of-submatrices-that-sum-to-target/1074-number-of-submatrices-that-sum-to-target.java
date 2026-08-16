class Solution {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
                int count=0;
        int n=matrix.length;
        int m=matrix[0].length;
        for(int top=0;top<n;top++)
        {
            int col[]=new int[m];
            for(int bottom=top;bottom<n;bottom++)
            {
                for(int c=0;c<m;c++)
                {
                    col[c]=col[c]+matrix[bottom][c];
                }
            
                for(int start=0;start<m;start++)
                {
                    int sum=0;
                    for(int end=start;end<m;end++)
                    {
                        sum+=col[end];
                        if(sum==target)
                        count++;

                    }
                }
            }
        }
        return count;
        
    }
}