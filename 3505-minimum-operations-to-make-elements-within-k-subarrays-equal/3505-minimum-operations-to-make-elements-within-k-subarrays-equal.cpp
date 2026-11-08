class Solution {
    static const int MAXN = 100001;
    static const int BITS = 11, BUCKETS = 1 << BITS, OFFSET = 1000000;

    // moves[i] = cost of the window of size x that ends at nums[i - 1]
    static inline long long moves[MAXN];
    // Rolling layers: [i] = min cost of j subarrays using the first i elements
    static inline long long dpA[MAXN], dpB[MAXN];
    static inline int order[MAXN], tmp[MAXN];
    // Nodes 0..n-1 are elements. Block b has sentinels n + 2b and n + 2b + 1.
    static inline int rnk[2 * MAXN + 2], prv[2 * MAXN + 2], nxt[2 * MAXN + 2];

    // Stable LSD radix sort of the indices by value. Two passes of 11 bits
    // cover the shifted range [0, 2e6], and the result ends up back in order.
    static void radixSort(const vector<int>& nums, int n) {
        for (int i = 0; i < n; i++) order[i] = i;
        int* src = order;
        int* dst = tmp;
        for (int shift = 0; shift < 2 * BITS; shift += BITS) {
            int start[BUCKETS + 1] = {};
            auto digit = [&](int i) { return ((nums[i] + OFFSET) >> shift) & (BUCKETS - 1); };
            for (int i = 0; i < n; i++) start[digit(i) + 1]++;
            for (int b = 0; b < BUCKETS; b++) start[b + 1] += start[b];
            for (int p = 0; p < n; p++) dst[start[digit(src[p])]++] = src[p];
            swap(src, dst);
        }
    }

public:
    long long minOperations(vector<int>& nums, int x, int k) {
        int n = nums.size();
        // Far above any real answer, and adding moves to it can't overflow.
        const long long INF = 1e18;

        radixSort(nums, n);

        // Ranks are all distinct, so duplicates need no special handling.
        // Heads rank below every element and tails above, so comparisons
        // work without checking for sentinels.
        int blocks = (n + x - 1) / x;
        auto head = [&](int b) { return n + 2 * b; };
        auto tail = [&](int b) { return n + 2 * b + 1; };
        for (int b = 0; b < blocks; b++) {
            rnk[head(b)] = -1;
            rnk[tail(b)] = n;
            nxt[head(b)] = tail(b);
            prv[tail(b)] = head(b);
        }

        // Walking the sorted order leaves every block's list sorted.
        for (int p = 0; p < n; p++) {
            int v = order[p], t = tail(v / x);
            rnk[v] = p;
            prv[v] = prv[t];
            nxt[v] = t;
            nxt[prv[v]] = v;
            prv[t] = v;
        }

        // Empty every block except the first. Going backwards means restoring
        // forwards is an exact undo: each node keeps its old neighbours.
        for (int i = n - 1; i >= x; i--) {
            nxt[prv[i]] = nxt[i];
            prv[nxt[i]] = prv[i];
        }

        // "low" = the lowCount smallest elements of the window; the median is
        // its largest. It's always a prefix of both lists: a and b point at
        // the last low element in the old block and in the new block.
        int lowCount = (x + 1) / 2, highCount = x - lowCount;
        long long sumLow = 0, windowSum = 0;
        for (int i = 0; i < x; i++) windowSum += nums[i];

        int a = head(0), b = head(0);
        for (int c = 0; c < lowCount; c++) {
            b = nxt[b];
            sumLow += nums[b];
        }

        auto cost = [&](int median) {
            long long med = nums[median];
            long long sumHigh = windowSum - sumLow;
            return (med * lowCount - sumLow) + (sumHigh - med * highCount);
        };

        moves[x] = cost(b);

        for (int i = x; i < n; i++) {
            // The old block is used up: the new block takes its place.
            if (i % x == 0) {
                a = b;
                b = head(i / x);
            }

            int out = i - x, count = lowCount;
            windowSum += nums[i] - nums[out];

            // Remove the leaving element from the old block.
            if (rnk[out] <= rnk[a]) {
                sumLow -= nums[out];
                count--;
                if (out == a) a = prv[out];
            }
            nxt[prv[out]] = nxt[out];
            prv[nxt[out]] = prv[out];

            // Restore the new element into the new block.
            nxt[prv[i]] = i;
            prv[nxt[i]] = i;
            if (rnk[i] < max(rnk[a], rnk[b])) {
                sumLow += nums[i];
                count++;
                if (rnk[i] > rnk[b]) b = i;
            }

            // Low is at most one element off, so one step fixes it.
            if (count < lowCount) {
                // Take the smallest high element.
                if (rnk[nxt[a]] < rnk[nxt[b]]) a = nxt[a];
                else b = nxt[b];
                sumLow += nums[rnk[a] > rnk[b] ? a : b];
            } else if (count > lowCount) {
                // Give back the largest low element.
                if (rnk[a] > rnk[b]) {
                    sumLow -= nums[a];
                    a = prv[a];
                } else {
                    sumLow -= nums[b];
                    b = prv[b];
                }
            }

            moves[i + 1] = cost(rnk[a] > rnk[b] ? a : b);
        }

        long long* prev = dpA;
        long long* cur = dpB;

        // Prefixes shorter than x can't fit a window, in any layer.
        for (int i = 0; i < x; i++) prev[i] = cur[i] = INF;

        // Layer 1: the cheapest single window in the first i elements.
        prev[x] = moves[x];
        for (int i = x + 1; i <= n; i++) prev[i] = min(prev[i - 1], moves[i]);

        for (int j = 2; j <= k; j++) {
            cur[x] = prev[0] + moves[x];
            for (int i = x + 1; i <= n; i++) {
                cur[i] = min(cur[i - 1], prev[i - x] + moves[i]);
            }
            swap(prev, cur);
        }

        return prev[n];
    }
};

auto init = []()
{ 
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    return 'c';
}();