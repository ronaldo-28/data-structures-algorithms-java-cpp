class Solution {
public:
    vector<vector<int>> r;
    vector<int> pre;
    int total;
    Solution(vector<vector<int>>& v) {
        r=v;
        int tot=0;
        vector<int> a;
        for(auto it:v){
            tot+=(it[2]-it[0]+1)*(it[3]-it[1]+1);
            a.push_back((it[2]-it[0]+1)*(it[3]-it[1]+1));
        }
        //total area:[0,tot)
        for(int i=1;i<a.size();i++){
            a[i]+=a[i-1];
        }
        total=tot;
        pre=a;
    }
    
    vector<int> pick() {
        int num=rand()%total;

        auto ind=upper_bound(pre.begin(),pre.end(),num)-pre.begin();
        
        int x1=r[ind][0],y1=r[ind][1],x2=r[ind][2],y2=r[ind][3];
        int lenX=x2-x1+1;
        int xRand=rand()%lenX;
        int chooseX=x1+xRand;

        int lenY=y2-y1+1;
        int yRand=rand()%lenY;
        int chooseY=y1+yRand;
        return {chooseX,chooseY};
    }
};
// 0,1,2,3

// -2, 1


// 3

/**
 * Your Solution object will be instantiated and called as such:
 * Solution* obj = new Solution(rects);
 * vector<int> param_1 = obj->pick();
 */


// 30 mins for each