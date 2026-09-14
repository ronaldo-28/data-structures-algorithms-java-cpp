class Solution {
    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];
        int n = s.length();
        int cnt = 0;
        long totalWays = 1L;
        for(int i=0;i<n/2;i++){
            freq[s.charAt(i)-'a']++;
        }

        char[] alpha = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
        };
        StringBuilder sb = new StringBuilder();
        outer : for(int i=25;i>=0;i--){
            if(freq[i]==0)continue;
            int j=1;
            while(j<=freq[i]){
                cnt++;
                totalWays = (totalWays*cnt)/j;
                if(totalWays>k){
                    for(int l = 0;l<i;l++){
                        while(freq[l]>0){
                            sb.append(alpha[l]);
                            freq[l]--;
                        }
                    }
                    while(freq[i]>j){
                        sb.append(alpha[i]);
                        freq[i]--;
                    }
                    break outer;
                }
                j++;
            }
        }
        if(k>totalWays)return "";
        
        
        for(int i=0;i<cnt;i++){
            for(char ch= 'a';ch<='z';ch++){
                int j = ch-'a';
                if(freq[j]==0)continue;
                if(k<=(totalWays*freq[j])/(cnt-i)){
                   totalWays = (totalWays*freq[j])/(cnt-i);
                   freq[j]--;
                   sb.append(ch);
                   break;
                }
                else{
                    k = (int)(k - (totalWays*freq[j])/(cnt-i));
                }
            }
        }
        if(n%2==1){
            sb.append(s.charAt(n/2));
        }

        for(int i=n/2-1;i>=0;i--){
            sb.append(sb.charAt(i));
        }

        return sb.toString();
    }
}