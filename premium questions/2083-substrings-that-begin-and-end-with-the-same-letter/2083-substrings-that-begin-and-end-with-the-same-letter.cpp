class Solution {
public:
  long long numberOfSubstrings(string s) {
    int freq[26]{};
    for (const char c:s) {
      freq[c-'a']++;
    }
    long long res=0;

    for (int f:freq) {
      res+=(long long)(f+1)*f/2;
    }
    return res;
  }
};