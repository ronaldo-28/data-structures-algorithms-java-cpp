class Solution {
public:
    using ll = long long;

    ll getSum(ll n, ll k) {
        return (2 * n - k) * (k + 1) / 2;
    }

    struct Shelf {
        int index;
        int numBooks;
    };

    long long maximumBooks(vector<int>& books) {
        ll bestAns = books[0];

        vector<Shelf> minStack;
        vector<ll> dp(books.size());

        minStack.push_back({0, books[0]});
        dp[0] = books[0];

        for (int i = 1; i < books.size(); i++) {
            Shelf curr{i, books[i]};

            while (!minStack.empty() &&
                   minStack.back().numBooks >=
                       curr.numBooks - (i - minStack.back().index)) {
                minStack.pop_back();
            }

            ll ans;
            if (minStack.empty()) {
                ll len = min((ll)books[i], (ll)i + 1);
                ans = getSum(books[i], len - 1);
            } else {
                auto top = minStack.back();
                ll len = min((ll)(i - top.index), (ll)books[i]);
                ans = dp[top.index] + getSum(books[i], len - 1);
            }

            dp[i] = ans;
            bestAns = max(bestAns, ans);
            minStack.push_back(curr);
        }

        return bestAns;
    }
};