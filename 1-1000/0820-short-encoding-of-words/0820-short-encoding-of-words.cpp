class Solution {
public:
struct reverseComparer {
  bool operator()(const string& a, const string& b) {
    for (auto pa = a.rbegin(), pb = b.rbegin(); pa != a.rend() && pb != b.rend(); ++pa, ++pb) if (*pa != *pb) return *pa < *pb;
    return a.size() < b.size();
  }
};
int minimumLengthEncoding(vector<string>& ws, int res = 0) {
  sort(ws.begin(), ws.end(), reverseComparer());
  for (auto i = 0; i < ws.size() - 1; ++i) res += ws[i].size() <= ws[i + 1].size() 
    && ws[i] == ws[i + 1].substr(ws[i + 1].size() - ws[i].size()) ? 0 : ws[i].size() + 1;
  return res + ws[ws.size() - 1].size() + 1;
}
};