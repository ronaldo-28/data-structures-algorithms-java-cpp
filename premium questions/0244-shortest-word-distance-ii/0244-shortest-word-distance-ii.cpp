class WordDistance {
public:
    WordDistance(vector<string>& wordsDict) {
        //wordsDictInt = wordsDict;
        
        for(int i = 0; i < wordsDict.size(); ++i)
        {
            auto& s = wordsDict[i];
            mymap[s].push_back(i);
        }
    }
    
    int shortest(string word1, string word2) {
        int out = INT_MAX;

        auto& v1 = mymap[word1];
        auto& v2 = mymap[word2];
        
        for(auto& a1 : v1)
        {
            for(auto& a2 : v2)
            {
                out = min(out, abs(a2-a1));
                /*
                if(a2 > a1)
                {
                    out = min(out, (a2-a1));
                }
                else
                {
                    out = min(out, (a1-a2));
                }
                */
            }
        }
        return out;
    }

private:
    unordered_map<string,vector<int>> mymap;  
    //vector<string> wordsDictInt;  
};

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance* obj = new WordDistance(wordsDict);
 * int param_1 = obj->shortest(word1,word2);
 */