class Solution {
    
    bool haha(char c) {
        return strchr("aeiouAEIOU", c);
    }
    
    void change1(string &key) {
         for (char &c : key) {
             c = tolower(c);
         }
    }
    
    void change2(string &key) {
    
        for (char &c : key) {
          if (haha(c)) {
              c = 'a';
          }
        }
    }
  
    
public:
    vector<string> spellchecker(vector<string>& wordlist, vector<string>& queries) {
        unordered_set<string> all;
        for (const string& w : wordlist) {
            all.insert(w);
        }
        unordered_map<string, int> map1, map2;
        for (int i = wordlist.size() - 1; i >= 0; --i) {
            string key = wordlist[i];
            change1(key);
            map1[key] = i;
            change2(key);
            map2[key] = i;
        }
        vector<string> r;
        for (const string& q : queries) {
            if (all.find(q) != all.end()) {
                r.push_back(q);
            } else {
                string key = q;
                change1(key);
                auto t = map1.find(key);
                if (t != map1.end()) {
                    r.push_back(wordlist[t->second]);
                } else {
                    change2(key);
                    t = map2.find(key);
                    r.push_back(t == map2.end() ? "" : wordlist[t->second]);
                }
            }
        }
        return r;
        
    }
};