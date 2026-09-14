using ll = long long;

class Solution {
    struct Item {
        int value;
        int freq;
        int msb;
    };

    struct Bucket {
        int total = 0;
        int bestFreq = 0;
        int bestValue = -1;
    };

    struct Candidate {
        int target;
        int targetFreq;
        int msb;
        ll lowerBound;
    };

public:
    ll minOperations(vector<int>& nums) {
        int n = nums.size();

        unordered_map<int, int> freq;
        freq.reserve(n * 2);
        freq.max_load_factor(0.7f);

        for (int x : nums)
            ++freq[x];

        if (freq.size() == 1)
            return 0;

        array<Bucket, 30> buckets{};
        vector<Item> values;
        values.reserve(freq.size());

        for (auto [x, f] : freq) {
            int msb = 31 - __builtin_clz(x);

            values.push_back({x, f, msb});

            auto& bucket = buckets[msb];
            bucket.total += f;

            if (f > bucket.bestFreq) {
                bucket.bestFreq = f;
                bucket.bestValue = x;
            }
        }

        vector<Candidate> candidates;

        // Bucket 0 contains only the value 1.
        // It cannot be the target unless all values are already 1.
        for (int b = 1; b < 30; ++b) {
            const auto& bucket = buckets[b];

            if (bucket.bestValue == -1)
                continue;

            // The target must be a strict majority of its bucket.
            if (2 * bucket.bestFreq <= bucket.total)
                continue;

            ll lowerBound =
                1LL * n + bucket.total - 2LL * bucket.bestFreq;

            candidates.push_back({
                bucket.bestValue,
                bucket.bestFreq,
                b,
                lowerBound
            });
        }

        // Examine the most promising candidates first.
        sort(candidates.begin(), candidates.end(),
             [](const Candidate& a, const Candidate& b) {
                 return a.lowerBound < b.lowerBound;
             });

        ll answer = n;

        for (const auto& candidate : candidates) {
            if (candidate.lowerBound >= answer)
                continue;

            int target = candidate.target;
            int targetBucket = candidate.msb;

            // Every other value in the same bucket requires two operations.
            ll operations =
                2LL * (buckets[targetBucket].total -
                       candidate.targetFreq);

            for (const auto& item : values) {
                // Already accounted for the entire target bucket.
                if (item.msb == targetBucket)
                    continue;

                bool oneOperation;

                if (item.value < target) {
                    // item.value -> target by multiplication
                    oneOperation = (target % item.value == 0);
                } else {
                    // item.value -> target by division
                    oneOperation = (item.value % target == 0);
                }

                operations += oneOperation
                    ? item.freq
                    : 2LL * item.freq;

                if (operations >= answer)
                    break;
            }

            answer = min(answer, operations);
        }

        return answer;
    }
};