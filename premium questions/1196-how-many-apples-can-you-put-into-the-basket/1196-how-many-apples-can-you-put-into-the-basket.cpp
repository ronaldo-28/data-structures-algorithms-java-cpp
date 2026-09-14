class Solution {
public:
    int maxNumberOfApples(vector<int>& weight) {
        int b = 0, e = weight.size()-1;

        int total = 5000;
        int ans = 0;
        while(b<=e) {
            int m = b + (e-b)/2;

            nth_element(begin(weight)+b, begin(weight)+m, begin(weight)+e+1);
            int sum = accumulate(begin(weight)+b, begin(weight)+m+1, 0);
            if (sum > total) {
                e = m-1;
            }
            else {
                ans = m+1;
                b = m+1;
                total -= sum;
            }
        }
        return ans;
    }
};