class Solution {

template<class U, class V>
struct PairHash {
    size_t operator()(const pair<U, V>& pair) const {
        size_t h1 = std::hash<U>{}(pair.first);
        size_t h2 = std::hash<V>{}(pair.second);

        return 31 * h1 + h2;
    }
};

public:
    bool isReflected(vector<vector<int>>& points) {
        unordered_set<pair<int, int>, PairHash<int, int>> set;
        int min = INT_MAX, max = INT_MIN;

        for (auto& p : points) {
            min = std::min(p[0], min);
            max = std::max(p[0], max);
        }

        for (auto& p : points) {
            set.insert({p[0] - min, p[1]});        
        }

        for (auto& p : points) {
            pair<int, int> newPoint = {max - p[0], p[1]};
            if (!set.contains(newPoint)) {
                return false;
            }
        }

        return true;
    }
};