class Solution {
public:
    vector<int> prisonAfterNDays(vector<int>& cells, int n) {
        int size = cells.size();
        vector<int> curr (size, 0);
        vector<int> prev = cells;
        n = (n-1)%14 + 1;
        while(n>0)
        {  
            for(int i = 0; i<size; i++){
                if(i-1 < 0 || i+1 == size) curr[i] = 0;
                else if(prev[i-1] == prev[i+1]) curr[i] = 1;
                else curr[i] = 0;
            }
            prev = curr;
            n--;
        }
        return curr;
    }
};