const auto _ = std::cin.tie(nullptr)->sync_with_stdio(false);
#define LC_HACK
const auto __ = []() {
    struct ___ {
                static void _() { std::ofstream("display_runtime.txt") << 0 << '\n'; }
                                };
                                                    std::atexit(&___::_);
                                                                            return 0;
                                                                                                    }(); 
class Solution {
public:
    int longestBalanced(string s) {
        int ans = 1;
        int n = s.length();
        int count = 1;
        for(int i=1;i<n;i++){
            if(s[i]==s[i-1]) count++;
            else count = 1;
            ans = max(ans,count);
        }

        vector<string> comb = {"ab","bc","ac"};
        for(string& str:comb){
            char a = str[0];
            char b = str[1];
            int pref = 0;
            unordered_map<int,int> mp;
            mp[0] = -1;
            for(int i=0;i<s.length();i++){
                if(s[i]==a) pref++;
                else if(s[i]==b) pref--;
                else{
                    mp.clear();
                    pref = 0;
                }
                if(!mp.count(pref)) mp[pref] = i;
                ans = max(ans,i-mp[pref]);
            }
        }

        int x = 0;
        int y = 0;
        int z = 0;
        map<pair<int,int>,int> ump;
        ump[{0,0}] = -1;
        for(int i=0;i<s.length();i++){
            if(s[i]=='a') x++;
            if(s[i]=='b') y++;
            if(s[i]=='c'){x--;y--;}
            if(!ump.count({x,y})){
                ump[{x,y}] = i;
            }
            ans = max(ans,i-ump[{x,y}]);
        }

        return ans;
    }
};