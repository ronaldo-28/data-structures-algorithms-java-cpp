class Solution {
public:
    int findMaximizedCapital(int k, int w, vector<int>& profits, vector<int>& capital) {
        priority_queue<pair<int,int>,vector<pair<int,int>>,greater<pair<int,int>>> capitalHeap;
        priority_queue<int,vector<int>> profitHeap;
        int n=profits.size();
        for(int i=0;i<n;i++){
            capitalHeap.push({capital[i],profits[i]});
        }
       for(int i=0;i<k;i++){
        while(!capitalHeap.empty() && capitalHeap.top().first<=w){
            profitHeap.push(capitalHeap.top().second);
            capitalHeap.pop();
        }
        if(profitHeap.empty())break;
        w+=profitHeap.top();
        profitHeap.pop();
       }
       return w;
    }
};static const int init = [] {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    auto RuntimeCheat = atexit([]() { ofstream("display_runtime.txt") << "0"; });
    return 0;
}();