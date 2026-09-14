using LL = long long;
class Solution {
    unordered_set<LL>Set;
    unordered_map<int, vector<int>>Map;
public:
    int longestValidSubstring(string word, vector<string>& forbidden) 
    {
        unordered_set<LL> forbiddenSet;
        
        // 1. 預處理非法單字：5-bit 壓縮編碼
        for (const auto& s : forbidden) {
            LL code = 0;
            for (char ch : s) {
                code = (code << 5) + (ch - 'a' + 1);
            }
            forbiddenSet.insert(code);
        }
        
        int n = word.size();
        int leftBound = 0;
        int ans = 0;
        
        // 2. 正向滑動視窗，逆向位元回溯
        for (int right = 0; right < n; ++right) {
            LL currentCode = 0;
            
            // 從右往左（從當前 right 開始往左數最多 10 個字元）
            for (int len = 1; len <= 10 && right - len + 1 >= leftBound; ++len) {
                int left = right - len + 1;
                
                // 💡 巧妙的逆向位元重組：
                // 當我們往左多看一個字元 word[left] 時，這個新字元其實是這個子字串的「最高位」！
                // 所以要把新字元放在最左邊： (word[left]-'a'+1) << (5 * (len - 1))
                currentCode = currentCode + ((LL)(word[left] - 'a' + 1) << (5 * (len - 1)));
                
                if (forbiddenSet.contains(currentCode)) {
                    leftBound = left + 1; // 撞到紅線，強力推擠左邊界
                    break;                // 剪枝，直接看下一個 right
                }
            }
            ans = max(ans, right - leftBound + 1);
        }
        
        return ans;
        
    }
    
    void helper(string&word, int len)
    {
        int n = word.size();
        LL code = 0;
        for (int i=0; i<n; i++)
        {
            if (i>=len) 
                code &= (1LL<<(5*(len-1)))-1;
                
            code = (code << 5) + word[i]-'a'+1;
                        
            if (i>=len-1 && Set.find(code)!=Set.end())
                Map[i-len+1].push_back(i);
        }
    }
};