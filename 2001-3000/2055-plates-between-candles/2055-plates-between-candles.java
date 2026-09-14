class Solution {
    public int[] platesBetweenCandles(String s, int[][] queries) {
        char arr[]=s.toCharArray();
        int n=s.length();
int sum[]=new int[n];
        int l[]=new int[n];
        int r[]=new int[n];
        int li=-1;
     int c=0;
        
        for(int i=0;i<arr.length;i++){
            if(arr[i]=='*'){
                c++;
            }
            if(arr[i]=='|'){
                li=i;
            }
            l[i]=li;
            sum[i]=c;
        }
        int lr=-1;
        for(int j=arr.length-1;j>=0;j--){
            if(arr[j]=='|'){
                lr=j;
            }
            r[j]=lr;
        }
        int res[]=new int[queries.length];

        for(int i=0;i<queries.length;i++){
            int a=r[queries[i][0]];
            int b=l[queries[i][1]];

            if(a!=-1 && b!=-1 && a<b){
                res[i]=sum[b]-sum[a];
            }
            
        }
        return res;
    }
}