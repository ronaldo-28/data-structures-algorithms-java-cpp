class Solution {
public:
    bool isPathCrossing(string path) {
        set<vector<int>> s;
        vector<int> now={0,0};
        s.insert(now);
        for(auto c:path){
            if(c=='N')now[1]++;
            if(c=='S')now[1]--;
            if(c=='E')now[0]++;
            if(c=='W')now[0]--;
            if(s.count(now))return true;
            s.insert(now);
        }
        return false;
    }
};