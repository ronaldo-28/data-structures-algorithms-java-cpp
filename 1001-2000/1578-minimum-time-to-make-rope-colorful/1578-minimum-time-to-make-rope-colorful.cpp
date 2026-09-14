class Solution {
public:
    int minCost(string colors, vector<int>& neededTime) {
        int min_cost=0;
        char prev = colors[0];
        int prev_index=0;
        for(int i=1; i<colors.length(); i++){
            if(colors[i]==prev){
                min_cost+=min(neededTime[prev_index],neededTime[i]);
                if(neededTime[prev_index]<=neededTime[i]){
                    prev=colors[i];
                    prev_index=i;
                }
            }
            else{
                prev=colors[i];
                prev_index=i;
            }
        }
        return min_cost;
    }
};