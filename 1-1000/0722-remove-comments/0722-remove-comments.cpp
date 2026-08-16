// O(Linear Traversal) Time and O(1) Space --> Linear Search Approach
class Solution {
    bool isLineComment(const string& s, int col){
        if(s[col] == '/' and col+1 != s.length() and s[col+1] == '/') return true;
        else return false;
    }
    bool isMultilineCommentStart(const string& s, int col){
        if(s[col] == '/' and col+1 != s.length() and s[col+1] == '*') return true;
        else return false;
    }
    bool isMultilineCommentEnd(const string& s, int col){
        if(s[col] == '*' and col+1 != s.length() and s[col+1] == '/') return true;
        else return false;
    }
public:
    vector<string> removeComments(vector<string>& source) {
        vector<string> result;
        for(int line = 0; line < source.size();){
            string currLine;
            for(int col = 0; col < source[line].size();){
                if(isLineComment(source[line], col)){break;}
                else if(isMultilineCommentStart(source[line], col)){
                    col += 2;
                    while(true){
                        if(col >= source[line].size()){line++; col = 0;}
                        if(isMultilineCommentEnd(source[line], col)){col += 2;break;}
                        else{col++;}
                    }
                }
                else{currLine.push_back(source[line][col++]);}
            }
            if(currLine.size()) result.push_back(currLine);
            line++;
        }
        return result;
    }
};