class Solution {
public:
    string answerString(string word, int numFriends) {
        if (numFriends == 1)
            return word;

        int index = duval(word);

        return word.substr(
            index,
            min((int)word.length() - index,
                (int)word.length() - numFriends + 1)
        );
    }

private:
    int duval(const string& word) {
        int i = 0, j = 1, k = 0;
        int n = word.length();

        while (j + k < n) {
            if (word[i + k] == word[j + k]) {
                k++;
            }
            else if (word[i + k] > word[j + k]) {
                j += k + 1;
                k = 0;
            }
            else {
                i = max(i + k + 1, j);
                j = i + 1;
                k = 0;
            }
        }

        return i;
    }
};
