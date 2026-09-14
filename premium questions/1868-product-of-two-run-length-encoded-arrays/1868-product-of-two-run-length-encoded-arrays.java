class Solution {
    public List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {
        List<List<Integer>> res = new ArrayList<>();
        int i = 0;
        int j = 0;
        int innerI = 0;
        int innerJ = 0;
        int count = 0;
        int currentValue = 0;
        while(i < encoded1.length && j < encoded2.length){
            int product = encoded1[i][0] * encoded2[j][0];
            if(currentValue == 0) currentValue = product;
            
            if(currentValue != product) {
                res.add(List.of(currentValue, count));
                count = 0;
                currentValue = product;
            }
            if(encoded1[i][1] == encoded2[j][1]){
                count += encoded1[i][1];
                i++;
                j++;
            } else if(encoded1[i][1] > encoded2[j][1]){
                count += encoded2[j][1];
                encoded1[i][1] = encoded1[i][1] - encoded2[j][1];
                j++;
            } else {
                count += encoded1[i][1];
                encoded2[j][1] = encoded2[j][1] - encoded1[i][1];
                i++;
            }
        }
        res.add(List.of(currentValue, count));
        return res;
    }
}