class Solution {
public:
    int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int l1,l2,b1,b2,area1,area2,ans;
        l1 = (ax1-ax2);
        l2 = (bx1-bx2);
        b1 = (ay1-ay2);
        b2 = (by1-by2);
        area1 = l1*b1;
        area2 = l2*b2;
        int p,q,r,s;
        int extra=0;
        p = max(ax1,bx1);
        q = max(ay1,by1);
        r = min(ax2,bx2);
        s = min(ay2,by2);
        if(p<r && q <s){
        extra = (p-r)*(q-s);
        }
        ans = area1 + area2 - extra;
        return ans;
    }
};