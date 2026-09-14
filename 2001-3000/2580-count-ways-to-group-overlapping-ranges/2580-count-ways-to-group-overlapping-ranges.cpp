class Solution {
public:
    int countWays(vector<vector<int>>& ranges) {
     vector<pair<int,int>>v;
        // creating a vector pair in order to sort the range groups
        for(auto& i:ranges){
            v.push_back({i[0],i[1]});
        }
        sort(v.begin(),v.end());
        int la=-1,ans=0;
        // maintaing the upper bound so that we can check whether this range 
        // is independent or inclusive of some previous range
        // here we are creating sets of ranges which will be together
        for(auto& i:v){
            if(i.first>la){
                ans++;
                la=i.second;
            }
            else la=max(la,i.second);
        }
        // calculating power via loop to avoid any overflows
        // calculating 2 to the power ans as and every set will have two options to go in either group one or two hence every set will have two choices so total ways equals 2 to the power no of sets
        int a=1;
        for(int i=0;i<ans;i++){
            a=(a*2)%1000000007;
        }
        return a;
    }
};