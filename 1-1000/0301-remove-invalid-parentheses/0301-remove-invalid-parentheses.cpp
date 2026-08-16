class Solution {
public:
    // holds the result
    vector<string> rst;
    // cstr - current pattern we are holdinf
    // open_count - number of ( opening paranthesis we have seen so far
    // skips - no of character we can skip
    // pos = postition int the input string we are working with
    void dfs(string &cstr, int open_count, int skips, int pos, string &str){
        // Base case
        if(pos==str.size()){
            // When we have reached end of pos all parenthesis should be balanced
            if(open_count) return;
            rst.push_back(cstr);
            return;
        }
        // We should only skip when the character getting skipped is not same as the last character we used , as we already skipped the same character in previous function call so skipping again will return in a duplicate pattern
        //However if we havent used any character then we can skip.
        if(skips && (cstr.size() && cstr.back()!=str[pos] || !cstr.size())) dfs(cstr,open_count,skips-1,pos+1,str);
        if(str[pos]=='(') open_count++;
        else if(str[pos]==')') open_count--;
        // if open_cout < 0 this implies we have more of closed paranthesis than open parantheis so this path will never lead to a valid solution
        if(open_count<0) return;
        cstr.push_back(str[pos]);
        dfs(cstr,open_count,skips,pos+1,str);
        // simple backtrack step to remove last charater
        cstr.pop_back();
    }
    // simple function using stack to identify unbalanced chars
    int calulete_invalid_characters(string &str){
        stack<int> stk;
        int invalid_count = 0;
        for(char c : str){
            if(c==')'){
                while(stk.size()&&stk.top()!='(') stk.pop();
                if(stk.size()&&stk.top()=='(') stk.pop();
                else invalid_count++;
            }else{
                stk.push(c);
            }
        }
        while(stk.size()) {
            if(stk.top()=='(') invalid_count++;
            stk.pop();
        }
        return invalid_count;
    }
    vector<string> removeInvalidParentheses(string str) {
        // get the minimum invalid characters
        int invalid_count = calulete_invalid_characters(str);
        // cstr holds the current patter
        string cstr;
        dfs(cstr,0,invalid_count,0,str);
        return rst;;
    }
};