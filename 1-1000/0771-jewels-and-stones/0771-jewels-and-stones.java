class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        int count =0;
        for(int i=0;i<stones.length();i++){
            char ch=stones.charAt(i);
            for(int j=0;j<jewels.length();j++){
                char ch1=jewels.charAt(j);
                if(ch==ch1){
                    count++;
                }
            }
        }
        return count;
        
    }
}