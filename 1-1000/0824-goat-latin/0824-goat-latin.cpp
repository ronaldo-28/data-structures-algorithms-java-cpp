class Solution {
public:
    string toGoatLatin(string sentence) {
        string vowels = "aeiouAEIOU";
        stringstream ss(sentence);
        string word, res;
        int idx = 1;
        while (ss >> word) {
            if (vowels.find(word[0]) != string::npos) {
                res += word;
            } else {
                res += word.substr(1) + word[0];
            }
            res += "ma";
            res += string(idx, 'a');
            res += " ";
            idx++;
        }
        res.pop_back();
        return res;
    }
};