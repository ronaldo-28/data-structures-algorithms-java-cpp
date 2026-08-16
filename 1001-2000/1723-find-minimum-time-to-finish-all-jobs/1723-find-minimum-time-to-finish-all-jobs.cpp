// TC: O(K^N ∗log(Sum))

class Solution {
public:
    bool possible(int jobIdx, vector<int> &workersTime, int maxTimeForEachWorker, int workers, vector<int>& jobs) {
        if(jobIdx == jobs.size()) return true;
        bool poss = false;
        for(int worker = 0; worker < workers; worker++) {
            if(workersTime[worker] + jobs[jobIdx] > maxTimeForEachWorker) continue;
            workersTime[worker] += jobs[jobIdx];
            poss |= possible(jobIdx + 1, workersTime, maxTimeForEachWorker, workers, jobs);
            if(poss) return poss;
            workersTime[worker] -= jobs[jobIdx];
            if (workersTime[worker] == 0) break;
        }
        return poss;
    }

    bool check(int maxTimeForEachWorker, int workers, vector<int>& jobs) {
        vector<int> workersTime(workers, 0); // currently no job is assigned to any worker
        return possible(0, workersTime, maxTimeForEachWorker, workers, jobs);
    }

    int minimumTimeRequired(vector<int>& jobs, int k) {
        sort(jobs.rbegin(), jobs.rend());
        int low = 0, high = accumulate(jobs.begin(), jobs.end(), 0), ans = -1;
        while(low <= high) {
            int mid = (low + high) / 2;
            if(check(mid, k, jobs)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
};