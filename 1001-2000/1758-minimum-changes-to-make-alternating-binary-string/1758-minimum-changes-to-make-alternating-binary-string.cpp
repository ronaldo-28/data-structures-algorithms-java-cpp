class Solution {
public:
   int minOperations(string s) {
    int in_place=0;
    for(int i=0; i<s.length(); i++){
        if(s[i]-'0'==i%2){
            in_place++;
        }
    }
    return min(in_place, (int)s.length()-in_place);
}
};