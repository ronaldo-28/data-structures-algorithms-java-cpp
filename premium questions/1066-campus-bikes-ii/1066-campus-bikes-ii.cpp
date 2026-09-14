class Solution {
private:
    // Get the number of possible states, where each bit represents if the
	// corresponding bike is picked.
    uint16_t getRange(int cnt) {
        uint16_t mask = 1;
        uint16_t res = 0;
        while(cnt-- > 0) {
            res |= mask;
            mask <<= 1;
        }
        return res;
    }
    
	// Calculate Manhaton distance
    int calDist(vector<int> &worker, vector<int> &bike) {
        return abs(worker[0] - bike[0]) + abs(worker[1] - bike[1]);
    }
    
    // Code is a little messy, but you get the idea
    int dfs(int w, int b, vector<vector<int>> &workers, vector<vector<int>> &bikes, vector<int> &memo, uint16_t usedBikes) {
        
		// Reached the bottom worker, start building up the distance bottom-up.
        if (w == workers.size() - 1) return calDist(workers[w], bikes[b]);
		
		// The minimum distance of the current state has been calculated,
		// use the result to build the solution bottom-up
        if (memo[usedBikes] != INT_MIN) return calDist(workers[w], bikes[b]) + memo[usedBikes];
        
		// For each possible next state, get the minimum distance.
        int minDist = INT_MAX;
        for (int i = 0; i < bikes.size(); i++) {
            if (usedBikes & (1 << i)) continue; // Bike is already taken
            minDist = min(minDist, dfs(w + 1, i, workers, bikes, memo, (usedBikes ^ (1 << i))));
        }
        
		// Memoize the optimal bottom-up distance of the current state
        memo[usedBikes] = minDist;
		
		// Build bottom-up distance
        return minDist + calDist(workers[w], bikes[b]);
    }
public:
    int assignBikes(vector<vector<int>>& workers, vector<vector<int>>& bikes) {
        vector<int> memo (getRange(bikes.size()) + 1, INT_MIN);
        int res = INT_MAX;
        
		// Initialize DFS by allowing worker 0 to pick each of the bikes.
        for (int i = 0; i < bikes.size(); i++) {
            res = min(res, dfs(0, i, workers, bikes, memo, (1 << i)));
        }
        return res;
    }
};