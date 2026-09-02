class Solution {
public:
    int minimumTime(vector<int>& jobs, vector<int>& workers) {
        sort(jobs.begin(), jobs.end());
        sort(workers.begin(), workers.end());
        int minTime = 0;
        for (int i = 0; i < jobs.size(); i++) {
            int duration = ceil((double) jobs[i] / (double) workers[i]);
            minTime = max(minTime, duration);
        }
        return minTime;
    }
};