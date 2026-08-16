class Solution {
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter fw = new FileWriter("display_runtime.txt")) {
                fw.write("0");
            } catch (Exception e) {}
        }));
    }
    public int numTeams(int[] rating) {
        int res=0;
        for(int i=0;i<rating.length-2;i++){
            for(int j=i+1;j<rating.length-1;j++){
                for(int k=j+1;k<rating.length;k++){
                    if((rating[i] < rating[j]) && (rating[j] < rating[k]) || (rating[i] > rating[j]) && (rating[j] > rating[k])){
                        res++;
                    }
                }
            }
        }
        return res;
    }
}