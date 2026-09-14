class Solution {
    public int countOdds(int low, int high) {
        int range=high-low+1;
        if(low%2==1 && high%2==1){
            return (range/2)+1;
        }
        return range/2;

    }
}