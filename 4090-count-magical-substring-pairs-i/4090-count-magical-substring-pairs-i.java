class Solution {
    public int countMagicalPairs(String s) {
        int n= s.length();
        int ans=0;

        for(int j=0 ;j<n-1; j++){
            int[] count= new int[27];
            int[] freq1= new int[26];
            int[] freq2= new int[26];
            int unique=0;

            for(int i=j; i>=0; i--){
                if(freq1[s.charAt(i)-'a']++ ==0) unique++;
                count[unique]++;
            }

            unique=0;
            
            for(int k=j+1; k<n; k++){
                if(freq2[s.charAt(k)-'a']++ ==0) unique++;
                ans+=count[unique];
            }
        }

        return ans;
    }
}