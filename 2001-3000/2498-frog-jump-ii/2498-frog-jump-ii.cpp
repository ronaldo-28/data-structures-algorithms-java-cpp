class Solution {
public:
    int maxJump(vector<int>& stones) {
        // Initial candidate jump (important for n = 2 as well).
        int ans = stones[1] - stones[0];

        // Greedy insight:
        // If we alternate stones between outward and return paths,
        // the critical jumps become stones[i] - stones[i-2].
        for (int i = 2; i < (int)stones.size(); ++i) {
            ans = max(ans, stones[i] - stones[i - 2]);
        }

        return ans;
    }
};