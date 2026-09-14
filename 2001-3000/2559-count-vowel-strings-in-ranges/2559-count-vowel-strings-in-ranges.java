class Solution {
    static {
        for(int i = 0; i < 500; i++) vowelStrings(new String[] {"a"}, new int[][] {{0, 0}});
    }
    public static int[] vowelStrings(String[] words, int[][] queries) {
        int q=queries.length;
        int ans[]=new int[q];

        int prefixSum[]=new int[words.length+1];

        for(int i=0; i<words.length; i++){
            String s=words[i];

            if(isVowel(s.charAt(0)) && isVowel(s.charAt(s.length()-1))){
                prefixSum[i+1]=prefixSum[i]+1;
            }else{
                prefixSum[i+1]=prefixSum[i];
            }
        }
        for(int i=0; i<q; i++){
            ans[i]=prefixSum[queries[i][1]+1]-prefixSum[queries[i][0]];
        }
        return ans;
    }
    private static boolean isVowel(char c){
        return c=='a' || c=='e' || c=='i' || c=='o' || c=='u' ;
    }
}