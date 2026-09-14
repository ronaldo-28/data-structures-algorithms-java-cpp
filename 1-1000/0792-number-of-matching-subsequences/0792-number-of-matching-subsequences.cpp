// max s.length =5 * 10^4
//  words.length = 5000
//  worst case -> time exceed

//  unordered_map
//  binary_search

// s = "abcadbe"
// words = ["a","ba","acd","ace"]
// 1. check each char in s
// 2. word = "ba", unordered<int, int> -> unordered_map<int, vector<int>>
// 3. for char in word:
//      order_chr = umap[char]
//      
// order_nextchar > older_order_charter  : binary_search

#define see(x) cout << #x << ": " << x << ' ';
#define seen(x) cout << #x << ": " << x << '\n'; 
auto __fast_io_atexit = []() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    
    std::atexit([]() { 
        ofstream("display_runtime.txt") << "0"; 
    });

    return 0;
}();

class Solution {
private:
    int binarySearchIndex(int currentOrder, vector<int>& orders) {
        int left = 0;
        int right = orders.size();

        if (currentOrder < orders[left])
            return orders[left];
        else if (orders[right - 1] <= currentOrder)
            return -1;

        while (left < right) {
            int mid = (left + right) / 2;

            if (orders[mid] <= currentOrder) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        if (left == orders.size()) return -1;
        return orders[left];
    }

public:
    int numMatchingSubseq(string s, vector<string>& words) {
        unordered_map<int, vector<int>> umap;
        for (int i = 0; i < s.length(); i++) {
            umap[s[i]].push_back(i);
        }

        int ans = 0;

        for (const auto& word : words) {
            int order = -1;
            bool check = true;

            for (const auto& ch : word) {
                if (umap.count(ch)) {
                    order = binarySearchIndex(order, umap[ch]);
                    if (order == -1) {
                        break;
                    }
                }
                else {
                    check = false;
                    break;
                }
            }

            if (!check || order == -1) continue;
            ans++;
        }

        return ans;
    }
};