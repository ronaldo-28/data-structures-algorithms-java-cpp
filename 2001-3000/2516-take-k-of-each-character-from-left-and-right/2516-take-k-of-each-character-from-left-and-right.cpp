class Solution {
public:
    int takeCharacters(string s, int k){
        int A = 0;
        int B = 0;
        int C = 0;
        for(char ch: s){
            if(ch == 'a'){
                A++;
            }else if(ch == 'b'){
                B++;
            }else if(ch == 'c'){
                C++;
            }
        }
        if(A < k || B < k || C < k){
            return -1;
        }
        A = A - k;//the middle can't exceed
        B = B - k;//the middle can't exceed
        C = C - k;//the middle can't exceed
        int n = s.size();
        int j = 0;
        int a = 0; 
        int b = 0; 
        int c = 0;
        int solution = 0;
        for(int i = 0; i < n; i++){
            while(a <= A && b <= B && c <= C){
                // [i,j)
                solution = max(solution, j - i);
                if(j == n){
                    break;
                }
                if(s[j] == 'a'){ 
                    a++;
                }else if(s[j] == 'b'){
                    b++;
                }else if(s[j] == 'c'){
                    c++;
                }
                j++;
            }
            if(s[i] == 'a'){
                a--;
            }else if(s[i] == 'b'){
                b--;
            }else if(s[i] == 'c'){
                c--;
            }
        }
        return n - solution;
    }
};