class Solution {
public:
    int countQuadruples(string firstString, string secondString) {
        vector<int>first(26,-1);
        vector<int>last(26,-1);
        for(int i=0;i<firstString.size();i++){
            int c=firstString[i]-'a';
            if(first[c]==-1){
                first[c]=i;
            }
        }
        for(int i=0;i<secondString.size();i++){
            last[secondString[i]-'a']=i;
        }
        int minimumDifference=INT_MAX;
        int ans=0;
        for(int c=0;c<26;c++){
            if(first[c]==-1||last[c]==-1){
                continue;
            }
            int difference=first[c]-last[c];
            if(difference<minimumDifference){
                minimumDifference=difference;
                ans=1;
            }else if(difference==minimumDifference){
                ans++;
            }
        }
        return ans;
    }
};