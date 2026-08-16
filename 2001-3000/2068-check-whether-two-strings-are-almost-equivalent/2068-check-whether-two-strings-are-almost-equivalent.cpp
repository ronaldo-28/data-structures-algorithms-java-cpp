class Solution {
public:
    bool checkAlmostEquivalent(string word1, string word2) {
        int n=word1.size();
        int m=word2.size();

       int hash1[26]={0};
       int hash2[26]={0};
       for(int i=0;i<n;i++){
            hash1[word1[i]-'a']+=1;
       }
       for(int i=0;i<m;i++){
        hash2[word2[i]-'a']+=1;
       }

       for(int i=0;i<n;i++){
        if(abs(hash1[word1[i]-'a']-hash2[word1[i]-'a'])>3) return false;
       }
        for(int i=0;i<m;i++){
        if(abs(hash1[word2[i]-'a']-hash2[word2[i]-'a'])>3) return false;
       }
       return true;
    }
};