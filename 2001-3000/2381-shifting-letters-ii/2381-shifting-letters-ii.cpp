class Solution {
public:
    string shiftingLetters(string s, vector<vector<int>>& shifts) {
int m=s.size();
vector<int>diff(m+1,0);
int n=shifts.size();
for(int i=0;i<n;i++){
    if(shifts[i][2]==0){
        diff[shifts[i][0]]--;
        diff[shifts[i][1]+1]++;
    }
    else{
        diff[shifts[i][0]]++;
        diff[shifts[i][1]+1]--;
    }
}
int sum=0;
for(int i=0;i<m;i++){
    sum+=diff[i];
    int pos=s[i]-'a';
    int shift=(((pos+sum)%26)+26)%26;
    s[i]='a'+shift;
    }
    return s;
    }
};