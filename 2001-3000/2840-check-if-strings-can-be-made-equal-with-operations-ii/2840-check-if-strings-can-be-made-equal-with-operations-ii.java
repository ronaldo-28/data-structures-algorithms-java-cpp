class Solution {
    public boolean checkStrings(String s1, String s2) {
        int  n = s1.length();
        int freq1[]=new int[26];
        for(int i=0;i<n;i+=2){
            freq1[s1.charAt(i)-'a']++;
        }
        int freq2[]=new int[26];
        for(int i=0;i<n;i+=2){
            freq2[s2.charAt(i)-'a']++;
        }
        for(int i =0;i<26;i++){
            if(freq1[i]!=freq2[i])return false;
            freq1[i]=0;
            freq2[i]=0;
        }
        for(int i=1;i<n;i+=2){
            freq1[s1.charAt(i)-'a']++;
        }
        for(int i=1;i<n;i+=2){
            freq2[s2.charAt(i)-'a']++;
        }
        for(int i =0;i<26;i++){
            if(freq1[i]!=freq2[i])return false;
        }
        return true;
    }
}