class Solution {
public:
    string maximumNumber(string num, vector<int>& change) {
        int n = num.size();
        bool start = false;
        for(int i = 0;i<n;i++){
            int ele = num[i] - '0';
            if(change[ele] > ele || (change[ele] == ele && start)){
                num[i] = change[ele] + '0';
                start = true;
            }else if(start){
                return num;
            }
        }
        return num;
    }
};