class Solution {
public:
    vector<int> assignTasks(vector<int>& servers, vector<int>& tasks) {
        // free: {weight, index}
        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<>> free;
        // busy: {finish_time, index}
        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<>> busy;
        
        int n = servers.size();
        int m = tasks.size();
        vector<int> res(m);

        for (int i = 0; i < n; i++) {
            free.push({servers[i], i});
        }

        long long cur_time = 0; // 使用 long long 防止時間溢位
        for (int i = 0; i < m; i++) {
            // 1. 時間至少要是任務到達的時間 i
            cur_time = max(cur_time, (long long)i);

            // 2. 如果目前沒有空閒伺服器，時間必須跳到下一個伺服器完工的時間
            if (free.empty()) {
                cur_time = busy.top().first;
            }

            // 3. 把所有在當前時間已經完工的伺服器移回 free
            while (!busy.empty() && busy.top().first <= cur_time) {
                int idx = busy.top().second;
                free.push({servers[idx], idx});
                busy.pop();
            }

            // 4. 分配任務給最優伺服器
            auto [w, idx] = free.top();
            free.pop();
            res[i] = idx;
            busy.push({cur_time + tasks[i], idx});
        }

        return res;
    }
};