class Solution {
public:
    int calculateTime(string keyboard, string word) {
        int ks[26];
        for(int i=0;i<26;i++) {
            ks[keyboard[i]-'a'] = i;
        }

        int result = ks[word[0]-'a'];
        int last_pos = result;
        for(int i=1;i<word.size();i++) {
            result += abs(ks[word[i]-'a'] - last_pos);
            last_pos = ks[word[i]-'a'];
        }

        return result;
    }
};