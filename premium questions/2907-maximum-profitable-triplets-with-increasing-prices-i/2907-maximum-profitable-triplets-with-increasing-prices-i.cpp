class Solution {
    void insert(int price, int profit, map<int, int> &have) {
        auto t = have.upper_bound(price);
        if (t != have.begin() && (--t)->second >= profit) return;
        for (t = have.lower_bound(price); t != have.end() && t->second <= profit; have.erase(t++))
        ;
        have[price] = profit; 
    }

public:
    int maxProfit(vector<int>& prices, vector<int>& profits) {
        map<int, int> one, two;
        const int n = prices.size();
        int r = -1;
        for (int i = 0; i < n; ++i) {
            auto t = two.lower_bound(prices[i]);
            if (t != two.begin()) {
                r = max(r, (--t)->second + profits[i]);
            }
            t = one.lower_bound(prices[i]);
            if (t != one.begin()) {
                insert(prices[i], (--t)->second + profits[i], two);
            }
            insert(prices[i], profits[i], one);
        }
        return r;
    }
};