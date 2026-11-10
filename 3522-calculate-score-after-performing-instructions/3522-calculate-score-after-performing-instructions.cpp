class Solution {
public:
    long long calculateScore(vector<string>& instructions, vector<int>& values) {
        int i=0, x, n=instructions.size(); long long val=0;
        while(i>-1 && i<n){
            if(values[i]==1e9) break;
            if(instructions[i][0]=='a'){val+=1LL*values[i]; values[i]=1e9; i++;}
            else{x=values[i]; values[i]=1e9; i+=x;}
        }
        return val;
    }
};