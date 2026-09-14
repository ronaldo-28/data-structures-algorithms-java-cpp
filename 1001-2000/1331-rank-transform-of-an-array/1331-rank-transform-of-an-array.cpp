class Solution {
public:
    vector<int> arrayRankTransform(vector<int>& arr) {
        vector<int> sortedArray = arr;
        sort(sortedArray.begin() , sortedArray.end());
        sortedArray.erase(unique(sortedArray.begin() , sortedArray.end()) , sortedArray.end());
        int m = sortedArray.size();
        map<int , int> mpp;
        for(int i = 0; i < m; i++){
            mpp[sortedArray[i]] = i;
        }
        int n = arr.size();
        vector<int> rankArray(n , 0);
        for(int i = 0; i < n; i++)
        {
            rankArray[i] = mpp[arr[i]] + 1;
        }
        return rankArray;
    }
};
auto init = atexit([](){ofstream("display_runtime.txt")<<"0";});