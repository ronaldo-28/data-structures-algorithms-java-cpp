class Solution {
    public int maxRectangleArea(int[][] points) {
        for(int i=0;i<points.length;i++)
        {
            for(int j=i+1;j<points.length;j++)
            {
                if(points[i][0]>points[j][0])
                {
                    int t1=points[i][0];
                    int t2=points[i][1];
                    points[i][0]=points[j][0];
                    points[i][1]=points[j][1];
                    points[j][0]=t1;
                    points[j][1]=t2;
                }
                else if(points[i][0]==points[j][0])
                {
                    if(points[i][1]>points[j][1])
                    {

                        int t1=points[i][0];
                        int t2=points[i][1];
                        points[i][0]=points[j][0];
                        points[i][1]=points[j][1];
                        points[j][0]=t1;
                        points[j][1]=t2;
                        
                    }
                }
            }
        }
        
        int ans=-1;
        for(int i=0;i<points.length-3;i++)
        {
            if(points[i][0]==points[i+1][0])
            {
                int b=points[i+1][1]-points[i][1];
                
                for(int j=i+2;j<points.length-1;j++)
                {
                    if(points[j][1]!=points[i][1])
                    {
                        if(points[j][1]<=points[i+1][1] && points[j][1]>=points[i][1])
                        {
                            break;
                        }
                    }
                    else{
                        if(points[j+1][0]==points[j][0] && points[j+1][1]==points[i+1][1])
                        {
                            
                            int l=points[j][0]-points[i][0];
                            
                            ans=Math.max(ans, l*b);
                            break;
                        }
                        else{
                            break;
                        }
                    }
                }
            }
        }
        return ans;

    }
}