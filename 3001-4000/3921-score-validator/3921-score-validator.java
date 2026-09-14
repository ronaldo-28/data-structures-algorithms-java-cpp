class Solution {
    public int[] scoreValidator(String[] events) {
        int counter = 0;
        int score = 0;
        int n= events.length;
        for(int i=0;i<n;i++){
            String str = events[i];
            if(str.length()==1){
                if(str.charAt(0)=='W'){
                    counter++;
                    if(counter==10) break;
                }
                else{
                    int c = Integer.parseInt(str);
                    score+=c;
                }
            }
            else{
                score++;
            }
        }
        return new int[]{score, counter};
    }
}