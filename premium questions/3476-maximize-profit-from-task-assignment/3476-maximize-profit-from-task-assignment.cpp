class Solution {
public:
    long long maxProfit(vector<int>& workers, vector<vector<int>>& tasks) {
        // cout<<"WORKERS BEFORE:\n";
        // for (int i = 0; i < workers.size(); i++) {
        //     cout<<workers[i]<<" ";
        // }
        // cout<<endl;

        sort(workers.begin(), workers.end());

        // cout<<"WORKERS AFTER:\n";
        // for (int i = 0; i < workers.size(); i++) {
        //     cout<<workers[i]<<" ";
        // }
        // cout<<endl<<endl;

        // cout<<"TASKS BEFORE:\n";
        // for (int i = 0; i < tasks.size(); i++) {
        //     cout<<tasks[i][0]<<" "<<tasks[i][1]<<endl;
        // }
        // cout<<endl;
        
        sort(tasks.begin(), tasks.end(), [](const vector<int>& lhs, const vector<int>& rhs) {
            if (lhs[0] == rhs[0]) {
                return lhs[1] > rhs[1];
            }

            return lhs[0] < rhs[0];
        });

        // cout<<"TASKS AFTER:\n";
        // for (int i = 0; i < tasks.size(); i++) {
        //     cout<<tasks[i][0]<<" "<<tasks[i][1]<<endl;
        // }
        // cout<<endl;

        int workerPointer = 0;
        int taskPointer = 0;

        long long result = 0;
        int bestProfitForAdditionalWorker = 0;
        while (workerPointer < workers.size() && taskPointer < tasks.size()) {
            if (tasks[taskPointer][0] < workers[workerPointer]) {
                bestProfitForAdditionalWorker = max(bestProfitForAdditionalWorker, tasks[taskPointer][1]);
                taskPointer++;
                continue;
            }

            if (workers[workerPointer] < tasks[taskPointer][0]) {
                workerPointer++;
                continue;
            }

            result += tasks[taskPointer][1];
            taskPointer++;
            workerPointer++;
        }

        while (taskPointer < tasks.size()) {
            bestProfitForAdditionalWorker = max(bestProfitForAdditionalWorker, tasks[taskPointer][1]);
            taskPointer++;
        }

        return result + bestProfitForAdditionalWorker;
    }
};