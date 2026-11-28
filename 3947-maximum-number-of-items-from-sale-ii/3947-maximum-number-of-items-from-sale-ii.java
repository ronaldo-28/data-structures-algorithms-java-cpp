class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n=items.length;
        int count[]=new int[n+1];
        long min=Long.MAX_VALUE;
        for(int i=0;i<n;i++){
            count[items[i][0]]++;
            if(items[i][1]<min){
                min=items[i][1];
            }
        }

        int temp[]=new int[n+1];
        for(int i=1;i<=n;i++){
            if(count[i]>0){
                int m=-1;
                for(int j=i;j<=n;j+=i){
                    m+=count[j];
                }
                temp[i]=m;
            }
        }

        long[] deal=new long[n];
        int dealcount=0;
        long limit=min*2;
        for(int i=0;i<n;i++){
            int first=items[i][0];
            int second=items[i][1];
            int m=temp[first];
            if(second<limit && m>0){               
               deal[dealcount++]=((long)second<<32)|m;
            }
        }
        Arrays.sort(deal,0,dealcount);
        long total=0;
        for(int i=0;i<dealcount;i++){
            long deals=deal[i];
            int m=(int)(deals>>32);
            int counts=(int)deals;
            if(budget>=m){
                long time=Math.min((long)counts,budget/m);
                budget-=time*m;
                total+=2*time;
            }
        }
        total+=budget/min;
        return (int)total;
    }
}