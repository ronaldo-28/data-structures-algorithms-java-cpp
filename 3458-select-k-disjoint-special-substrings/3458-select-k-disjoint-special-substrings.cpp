class Solution {
public:
    bool maxSubstringLength(string s, int k) {
        if (k == 0) return true;
        
        int n = s.length();
        vector<int> first(26, -1), last(26, -1);
        
        // 1. 記錄每個字元的最早與最晚出現位置
        for (int i = 0; i < n; ++i) {
            int idx = s[i] - 'a';
            if (first[idx] == -1) first[idx] = i;
            last[idx] = i;
        }
        
        vector<pair<int, int>> intervals; // {R, L}
        
        // 2. 嘗試從每個字元的最早位置做區間擴展
        for (int i = 0; i < 26; ++i) {
            if (first[i] == -1) continue;
            
            int L = first[i];
            int R = last[i];
            bool valid = true;
            
            for (int j = L; j <= R; ++j) {
                int ch = s[j] - 'a';
                if (first[ch] < L) {
                    valid = false;
                    break;
                }
                R = max(R, last[ch]);
            }
            
            // 題目規定：Special substring 不能是整個字串 s
            if (valid && !(L == 0 && R == n - 1)) {
                intervals.push_back({R, L});
            }
        }
        
        // 3. 按右端點 R 排序
        sort(intervals.begin(), intervals.end());
        
        // 4. 貪婪選取最多不重疊區間
        int count = 0;
        int last_r = -1;
        
        for (auto& [r, l] : intervals) {
            if (l > last_r) {
                count++;
                last_r = r;
            }
        }
        
        return count >= k;    
    }
};