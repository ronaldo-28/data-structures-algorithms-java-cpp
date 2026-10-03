class Solution {
public:
    bool isValid(string word) {
        int c=0, v=0, n=word.size();
        if(n<3) return false;

        for(char ch:word){
            if(isalnum(ch)){
                ch=tolower(ch);
                if(ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u'){
                    v++;
                }
                else if(ch<='z' && ch>='a'){
                    c++;
                }
            }
            else
                return false;
        }

        return (c>0) && (v>0);
    }
};