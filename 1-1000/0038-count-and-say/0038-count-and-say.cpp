class Solution {
public:
    string RLE(string s){
        string result = "";
        for(int i =0 ;i < s.size(); i++){
            int count = 1;
            i++;
            while(i < s.size() && s[i] == s[i-1] ){
                count++;
                i++;
            }
            result += '0' + count;
            result += s[i-1]; 
            i--;

        }
        return result;
        
    }
    string countAndSay(int n) {
        if(n == 1){
            return "1";
        }
        return RLE(countAndSay(n-1));
    }
};