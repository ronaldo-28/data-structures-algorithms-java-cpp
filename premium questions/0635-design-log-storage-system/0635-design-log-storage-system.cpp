class LogSystem {
public:
    LogSystem() {
        
    }
    
    void put(int id, string timestamp) {
        logs.insert({timestamp, id});
    }
    
    vector<int> retrieve(string start, string end, string granularity) {
        vector<int> answer;
        adjustGranularity(start, end, granularity);

        auto it = logs.lower_bound({start, INT_MIN});
        auto stop = logs.upper_bound({end, INT_MAX});

        while (it != stop) {
            answer.push_back(it->second);
            ++it;
        }

        return answer;
    }

private:
    set<pair<string, int>> logs;

    void adjustGranularity(string& start, string& end, string& granularity) {
        // round down start, round up end
        if (granularity == "Second") return;
        vector<string> granuls = {"Minute", "Hour", "Day", "Month", "Year"};
        int idx = start.size() - 2;
        for (auto& g : granuls) {
            start[idx] = '0';
            start[idx+1] = '0';
            end[idx] = '5';
            end[idx+1] = '9';

            if (g == granularity) return;

            idx -= 3;
        }
    }
};

/**
 * Your LogSystem object will be instantiated and called as such:
 * LogSystem* obj = new LogSystem();
 * obj->put(id,timestamp);
 * vector<int> param_2 = obj->retrieve(start,end,granularity);
 */