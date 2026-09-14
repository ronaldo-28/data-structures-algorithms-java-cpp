class Solution {
public:
    vector<int> circularPermutation(int n, int start) {
        int size= 1<<n; 
        vector<int>ans(size); 
        int index=-1; 
        for(int i=0; i< size; i++) {
            ans[i]= (i ^i>>1);
            if(ans[i]==start) index= i;
        }
        reverse(ans.begin(), ans.begin()+index); 
        reverse(ans.begin()+index, ans.end());
        reverse(ans.begin(), ans.end()); 
        return ans; 
    }
};