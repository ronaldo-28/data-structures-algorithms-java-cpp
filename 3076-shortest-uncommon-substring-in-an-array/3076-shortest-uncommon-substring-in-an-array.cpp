class Solution {
private:
    struct TrieNode {
        int ch[26];
        int cnt, last;

        TrieNode() {
            fill(ch, ch + 26, -1);
            cnt = 0;
            last = -1;
        }
    };

    vector<TrieNode> trie;

public:
    vector<string> shortestSubstrings(vector<string>& arr) {
        trie.push_back(TrieNode());

        int n = arr.size();
        for(int id = 0; id < n; id++) {
            string &s = arr[id];
            int m = s.size();

            for(int i = 0; i < s.size(); i++) {
                int u = 0;
                for(int j = i; j < s.size(); j++) {
                    int x = s[j]-'a';

                    if(trie[u].ch[x] == -1) {
                        trie[u].ch[x] = trie.size();
                        trie.push_back(TrieNode());
                    }

                    u = trie[u].ch[x];

                    if(trie[u].last != id) {
                        trie[u].cnt++;
                        trie[u].last = id;
                    }
                }
            }
        }
        vector<string> ans;

        for(auto &s: arr) {
            string best = "";
            int m = s.size();
            for(int i = 0; i < m; i++) {
                int u = 0;
                for(int j = i; j < m; j++) {
                    u = trie[u].ch[s[j] - 'a'];
                    if(trie[u].cnt == 1) {
                        string curr = s.substr(i, j - i + 1);

                        if(best.empty() || curr.size() < best.size() || (curr.size() == best.size() && curr < best)) {
                            best = curr;
                        }

                        break;
                    }

                }
            }
            ans.push_back(best);
        }

        return ans;
    }
};