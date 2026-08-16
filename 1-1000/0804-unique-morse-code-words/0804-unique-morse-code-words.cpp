class Solution {
public:
    int uniqueMorseRepresentations(vector<string>& words) {
        vector<string> morse = {
            ".-","-...","-.-.","-..",".","..-.","--.","....","..",
            ".---","-.-",".-..","--","-.","---",".--.","--.-",".-.",
            "...","-","..-","...-",".--","-..-","-.--","--.."
        };

        unordered_set<string> uniqueCodes;

        for (string word : words) {
            string code = "";
            for (char c : word) {
                code += morse[c - 'a'];
            }
            uniqueCodes.insert(code);
        }

        return uniqueCodes.size();
    }
};