typedef pair<char,int> pii;
class Solution {
public:
    string frequencySort(string s) {
        unordered_map<int,int>mp;
        for(auto &ch : s){
            mp[ch]++;
        }
        vector<pii>nums;

        for(auto &itr: mp){
            char ch = itr.first;
            int count = itr.second;
            nums.push_back({ch,count});
        }

        auto comp = [](const pii &a, const pii &b){
            if(a.second != b.second){
                return a.second > b.second;
            }
            return a.first > b.first;
        };
        sort(nums.begin(),nums.end(),comp);
        string ans="";
        for(pii & num : nums){

            while(num.second > 0){
                ans.push_back(num.first);
                num.second--;
            }

        }
        return ans;
    }
};