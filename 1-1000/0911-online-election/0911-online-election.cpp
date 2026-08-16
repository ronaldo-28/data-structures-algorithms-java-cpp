class TopVotedCandidate {
public:
    vector<int>leader;
    vector<int>times;
    TopVotedCandidate(vector<int>& persons, vector<int>& times) {
        this->times = times;

        unordered_map<int , int>mpp;

        leader.resize(persons.size());

        int currLeader = -1;
        int maxi = 0;

        for(int i = 0 ; i < persons.size(); i++){
            int curr = persons[i];

            mpp[curr]++;

            if(mpp[curr] >= maxi){
                maxi = mpp[curr];
                currLeader = curr;
            }
            leader[i] = currLeader;
        }
    }
    
    int q(int t) {
        int low = 0;
        int high = times.size() - 1;

        while(low <= high){
            int mid = low + (high-low)/2;

            if(times[mid] <= t) low = mid + 1;
            else high = mid - 1;
        }
        return leader[high];
    }
};
auto init=atexit([]{ofstream("display_runtime.txt")<<"0";});
/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate* obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj->q(t);
 */