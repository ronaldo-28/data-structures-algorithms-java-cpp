class Solution {
    static{
        for(int i = 0; i < 500; i++){
            characterReplacement("", 0);
        }
    }
    public static int characterReplacement(String s, int k) {
        int n=s.length();
        if(n==1) return 1;
        int[] arr = new int[26];
        int i=0, j=0, maxFreq=0, ans=0;
        while(j<n){
            char ch = s.charAt(j++);
            arr[ch - 'A']++;
            maxFreq = Math.max(arr[ch - 'A'], maxFreq);
            if(j-i-maxFreq>k){
                arr[s.charAt(i) - 'A']--;
                i++;
                ans = Math.max(ans, j-i);
            }
        }
        return Math.max(ans, j-i);
    }
}