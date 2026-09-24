class Solution {
public:
    int minBishopMoves(vector<int>& source, vector<int>& target) {

        int s1=source[0], s2=source[1];
        int t1=target[0], t2=target[1];

        if( abs(s1-s2)%2 != abs(t1-t2)%2 ) return -1;
        if( abs(s1-t1) == abs(s2-t2) ) return 1;
        return 2;
    }
};