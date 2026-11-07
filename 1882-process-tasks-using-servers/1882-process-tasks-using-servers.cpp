class Solution {
public:
    vector<int> assignTasks(vector<int>& servers, vector<int>& tasks) {
        // weight, server
        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> free;
        vector<int> ans(tasks.size());


        // finish, server
        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> busy;
        for(int i = 0; i < servers.size(); i++) {
            free.push({servers[i], i});
        }
        long long time = 0;
        for(int i = 0; i < tasks.size(); i++) {
            time = max(time, (long long) i);
            if(free.empty()) {
                time =  (long long)busy.top().first;
            }

            while(!busy.empty() && time >= busy.top().first) {
                int free_index = busy.top().second;
                busy.pop();
                free.push({servers[free_index], free_index});
            }

            busy.push({time + tasks[i], free.top().second});
            int id = free.top().second;
            ans[i] = id;
            free.pop();
        }
        return ans;


    }
};