class Solution {
    public int[] circularGameLosers(int n, int k) {

        boolean[] visited = new boolean[n];
        int turn = 1;
        int currentPosition = 0;
        int loosercount = n;

        while(!visited[currentPosition]){
            if(!visited[currentPosition]){
                visited[currentPosition] = true;
                loosercount--;
                currentPosition = (currentPosition + turn* k)%n;
                turn++;
            }
        }
int j = 0;
     //   List<Integer> loosersList = new ArrayList<>();
     int[] result = new int[loosercount];
        for(int i =0;i<n;i++){
            
            if(!visited[i]){
                result[j] = i+1;
                j++;
            }
            
        }
    //    int m = loosersList.size();
        

        // for(int i=0;i<m;i++ ){
        //     result[i] = loosersList.get(i);
        // }

        return result;
    }
}