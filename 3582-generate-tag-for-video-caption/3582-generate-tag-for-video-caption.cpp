class Solution {
public:
    string generateTag(string caption) {
        int n = caption.size();
        string ans = "#";

        for(int i = 0 ; i < n ; i++) {
            if(caption[i] == ' ')continue;

            if(i > 0 && caption[i - 1] == ' ') {
                if(islower(caption[i])) caption[i] = toupper(caption[i]);
            } 
            else {
                if(isupper(caption[i])) caption[i] = tolower(caption[i]);
            }

             ans += caption[i]; 
        }

        if(ans.size() >= 1) ans[1] =  tolower(ans[1]);// here in the ques it is given 
        // that the first char should be small so im just checking if its small or not
        // and if it is i make this as small
        
        return ans.substr(0,100);// question says that ans should be max of 100 char 
    
    }
};