class Solution {
public:
    string freqAlphabets(string s) {
    string res;
    for (int i = 0; i < s.size();) {
    if (i < s.size() - 2 and s[i + 2] == '#') {
      res += 'j' + (s[i] - '1') * 10 + s[i + 1] - '0';
      i += 3;
    }
    else {
    res += 'a' + (s[i] - '1');
    i++;
    }
  }
  return res;
}
};