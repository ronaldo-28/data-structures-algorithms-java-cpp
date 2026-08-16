class Solution {
private:
    int operation(int x, int y, char op){
        if(op=='+') return x+y;
        if(op=='-') return x-y;
        return x*y;
    }
public:
    vector<int> diffWaysToCompute(string expression) {
        string s = expression;
        int n = s.length();

        //seperate the numbers and operators from the string
        vector<int> numbers;
        vector<char> op;
        int i=0; 
        while(i<n){
            if(s[i]=='+' || s[i]=='-' || s[i]=='*'){
                op.push_back(s[i]);
                i++;
            }
            else {
                if(i<n-1 && !(s[i+1]=='+' || s[i+1]=='-' || s[i+1]=='*')){//that means it is a 2 digit number
                    numbers.push_back((s[i]-'0')*10 + (s[i+1]-'0'));
                    i+=2;
                } else {//it is a 1 digit number
                    numbers.push_back(s[i]-'0');
                    i++;
                }
            }
        }

        //declare the dp array with size numbers.size();
        int sz = numbers.size();
        vector<vector<vector<int>>> dp(sz, vector<vector<int>>(sz));

        //base case, for 1 digit the digit itself is the result
        for(int i=0; i<sz; i++){
            dp[i][i].push_back(numbers[i]);//for only digit positions, not operators
        }

        //transition
        for(int len=2; len<=sz; len++){
            for(int i=0; i+len-1<=sz-1; i++){
                int j = i+len-1;
                for(int k=i; k<j; k++){
                    //push every posible result with this tow ranges
                    char oper = op[k];//op[k] is the operator between i to k and k+1 to j
                    for(auto x: dp[i][k]){
                        for(auto y: dp[k+1][j]){
                            dp[i][j].push_back(operation(x,y,oper));
                        }
                    }
                }
            }
        }
        
        //store the final results from dp[0][sz-1]
        vector<int> ans;
        for(auto it: dp[0][sz-1]) ans.push_back(it);
        return ans;
    }
};