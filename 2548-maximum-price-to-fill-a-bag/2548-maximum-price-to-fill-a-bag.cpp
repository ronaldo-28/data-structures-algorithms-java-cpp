static bool cmp(vector<int>&a,vector<int>&b){
    return 1LL* a[0]*b[1]>a[1]*b[0];
}
class Solution {
public:
    double maxPrice(vector<vector<int>>&it,int c){
       
        double ans=0;
        int w=0;
        for(int i=0;i<it.size();i++){
            w+=it[i][1];
        }
        if(w<c)return -1;
        sort(it.begin(),it.end(),cmp);
        for(int i=0;i<it.size();i++){
            if(it[i][1]<=c){

                ans+=it[i][0];
                c-=it[i][1];
            }
            else{
                ans+=c*(double)it[i][0]/it[i][1];
                break;
            }
        }
        return ans;
   
    }
};