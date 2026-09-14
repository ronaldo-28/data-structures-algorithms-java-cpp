class Solution {
public:
    string generateTheString(int n) {
        string s(n-1, 'a');
        s += n%2==0? 'b':'a';
        return s;
    }
};