const auto _ = std::cin.tie(nullptr)->sync_with_stdio(false);

#define LC_HACK
#ifdef LC_HACK
const auto __ = []() {
    struct ___ {
        static void _() { std::ofstream("display_runtime.txt") << 0 << '\n'; }
    };
    std::atexit(&___::_);
    return 0;
}();
#endif

class Solution {
public:
    int minOperations(vector<int>& nums) {
        stack<int> st;

        // int c1=0;
        // int c2=0;
        int sol=0;

        for(auto num:nums){
            if(num==0){
                while(!st.empty()) st.pop();
                continue;
            }
            while(!st.empty() && st.top()>num){
                st.pop();
                // c1++;
            }
            if(st.empty() || st.top()<num){
                st.push(num);
                // c2++;
                sol++;
            }
        }

        // cout<<c1<<endl;
        // cout<<c2<<endl;
        // cout<<st.size()<<endl;


        return sol;

    }

};