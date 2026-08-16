class Solution {
    public boolean checkForPos(int pos, int[][] triplets, int[] target){
        for(int i = 0; i < triplets.length; i++){
            int a = triplets[i][0];
            int b = triplets[i][1];
            int c = triplets[i][2];
            if(pos == 1){
                if(a == target[0] && b <= target[1] && c <= target[2])
                    return true;
            }
            else if(pos == 2){
                if(a <= target[0] && b == target[1] && c <= target[2])
                    return true;
            }
            else if(pos == 3){
                if(a <= target[0] && b <= target[1] && c == target[2])
                    return true;
            }
        }
        return false;
    }
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        boolean ans = checkForPos(3, triplets, target) && checkForPos(1, triplets, target) && checkForPos(2, triplets, target);
        return ans;
    }
}