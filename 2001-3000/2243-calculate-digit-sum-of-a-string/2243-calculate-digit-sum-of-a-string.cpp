class Solution {
public:
    string digitSum(string s, int k) {
        vector<int> result;
        if(s.size()<=k){
            return s;
        }
        string S = "";
        for(int i = 0; i < s.size(); i++){
            result.push_back(s[i] - '0');
        }

        for(int i = 0; i < result.size(); i += k){
            int num = 0;
            for(int j = i; j < i + k && j < result.size(); j++){
                num += result[j];
            }
            S += to_string(num);
        }
        if(S.size() <= k){
            return S;
        }
        else{
            return digitSum(S, k);
        }
    }
};