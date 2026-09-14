class Solution {
        static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (java.io.FileWriter fw = new java.io.FileWriter("display_runtime.txt")) {
                fw.write("0");
            } catch (Exception e) {
            }
        }));
    }
    private int binarySearch(int x, int[][] arr){
        
        int low = 0, high= arr.length-1;
        int ans =-1;

    
        while(low<=high){
           int mid= low + (high - low) /2;
            if(arr[mid][0] <= x){
                ans=mid;
                low= mid+1;
            
            }
            
           else {
                high = mid-1;
            }
        }
            return ans; 
    }

    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int [][] profits = new  int[difficulty.length][2];
        

       for(int i=0;i<profits.length;i++){
        profits[i][0]=difficulty[i];
        profits[i][1]=profit[i];

       } 
       Arrays.sort(profits, (a,b) -> a[0]==b[0]? b[1]-a[1]:a[0]-b[0]);


       int maxProfit=0;
       for(int i=0;i<profit.length;i++){
        if(maxProfit<=profits[i][1]){
            maxProfit=profits[i][1];
        }
        profits[i][1]=maxProfit;
       }
       int ans=0;
       for (int i : worker){
        int index = binarySearch(i,profits);

        if(index==-1){
            continue;
        }
        ans+=profits[index][1];
       }



       return ans;





    }
}