class Solution {
public:
    int brightestPosition(vector<vector<int>>& lights) {
        int brightest = 0;
        int brightestPos = 0;

        vector<int> starts(lights.size());
        vector<int> ends(lights.size());
        for(int i = 0; i < lights.size(); i++){
            starts[i] = lights[i][0] - lights[i][1];
            ends[i] = lights[i][0] + lights[i][1] + 1;
        }

        int active = 0;
        int ended = 0;
        sort(starts.begin(), starts.end());
        sort(ends.begin(), ends.end());

        for(int i = 0; i < lights.size(); i++){
            if(starts[i] >= ends[ended]){
                ended++;
            }
            else{
                active++;
            }
            if(brightest < active){
                brightest = active;
                brightestPos = starts[i];
            }
        }
        return brightestPos;
    }
};