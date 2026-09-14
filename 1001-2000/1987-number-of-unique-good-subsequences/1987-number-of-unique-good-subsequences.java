class Solution {
    public int numberOfUniqueGoodSubsequences(String binary) {
        int n = binary.length();
        int mod = (int)1e9 + 7; int ends0 = 0; int ends1 = 0; int has0 = 0;

        for(int i = 0; i<n; i++){
            if(binary.charAt(i) == '1'){
                ends1 = (ends1+ends0+1)%mod;
            }else{
                ends0 = (ends1+ends0)%mod;
                has0 = 1;
            }
        }

        return (ends1+ends0+has0)%mod;
    }
}