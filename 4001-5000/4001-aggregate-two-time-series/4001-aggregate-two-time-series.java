class Solution {
    public List<List<Integer>> aggregateTimeSeries(int[][] series1, int[][] series2) {
       List<List<Integer>>ans=new ArrayList<>();
        int i=0,j=0;
       
        while(i<series1.length&&j<series2.length){
            if(series1[i][0]==series2[j][0]){
                ans.add(Arrays.asList(series1[i][0],series2[j][1]+series1[i][1]));
                i++;j++;
                
            }
            else if(series1[i][0]<series2[j][0]){
                ans.add(Arrays.asList(series1[i][0],series1[i][1]+series2[j][1]));
                i++;
            }
            else{
                ans.add(Arrays.asList(series2[j][0],series2[j][1]+series1[i][1]));
            j++;
            }
        }
        while(i<series1.length){
               ans.add(Arrays.asList(series1[i][0],series1[i][1]));
            i++;
        }
        while(j<series2.length){
            ans.add(Arrays.asList(series2[j][0],series2[j][1]));
            j++;
        }
        return ans;
    }
}