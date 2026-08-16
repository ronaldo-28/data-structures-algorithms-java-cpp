class Solution {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] cnt = new int[12];
        for(String word : words) cnt[f(word)]++;
        for(int i = cnt.length - 2; i >= 0; i--){
            cnt[i] += cnt[i + 1];
        }
        int[] ans = new int[queries.length];
        for(int i = 0; i < queries.length; i++){
            ans[i] = cnt[f(queries[i]) + 1];
        }
        return ans;
    }

    public int f(String a){
        int cnt = 0;
        char min = 'z' + 1;
        for(int i = 0; i < a.length(); i++){
            char iChar = a.charAt(i);
            if(iChar < min){
                min = iChar;
                cnt = 1;
            }
            else if(iChar == min) cnt++;
        }
        return cnt;
    }
}