class Solution {
    static {
        for (int i=0; i<500; i++){
            findAnagrams("", "");
        }
    }
    public static List<Integer> findAnagrams(String s, String p) {
        int count = 0;
        int[] freq = new int[26];
        for(int i=0;i<26;i++)   freq[i]=-1;
        for(int i=0;i<p.length(); i++){
            if(freq[p.charAt(i) - 'a'] == -1)   freq[p.charAt(i) - 'a'] = 1;
            else freq[p.charAt(i) - 'a']++;
            count++;
        }
        List<Integer> ans = new ArrayList<>();
        int first = 0;
        int second = 0;
        while(second < s.length()){
            if(freq[s.charAt(second) - 'a'] > 0){
                freq[s.charAt(second) - 'a']--;
                count--;
                second++;
                if(count == 0){
                    ans.add(first);
                    freq[s.charAt(first) - 'a']++;
                    count++;
                    first++;
                }
            }
            else if(freq[s.charAt(second) - 'a'] == 0){
                freq[s.charAt(first) - 'a']++;
                count++;
                first++;
            }
            else{
                while(first!=second){
                    freq[s.charAt(first) - 'a']++;
                    count++;
                    first++;
                }
                first++;second++;
            }
        }
        return ans;
    }
}