class Solution {
public:
    string reformatNumber(string number) {
        string s = "", ans = "";
        bool check = false;
        for(int i = 0; i < number.size(); i++){
            if(number[i] == ' ' || number[i] == '-') continue;
            else s += number[i];
        }
        if(s.size() <= 3) return s;
        int n = s.size() % 3;
        if(n == 0){
            int count = 0;
            for(int i = 0; i < s.size(); i++){
                if(count == 3){
                    ans += '-';
                    count = 0;
                }
                ans += s[i];
                count++;
            }
        }
        if(n == 1){
            int i,count = 0;
            bool check = false;
            for(i = 0; i < s.size()-4; i++){
                check = true;
                if(count == 3){
                    ans += '-';
                    count = 0;
                }
                ans += s[i];
                count++;
            }
            if(check == true){
                ans += '-';
                ans += s[i++];
                ans += s[i++];
                ans += '-';
                ans += s[i++];
                ans += s[i];   
            }
            else{
                ans += s[i++];
                ans += s[i++];
                ans += '-';
                ans += s[i++];
                ans += s[i]; 
            }
        }
        if(n == 2){
            int i,count = 0;
            for(i = 0; i < s.size()-2; i++){
                if(count == 3){
                    ans += '-';
                    count = 0;
                }
                ans += s[i];
                count++;
            }
            ans += '-';
            ans += s[i++];
            ans += s[i];
        }
        return ans;
    }
};