class Solution {
public:
    long long numberOfWeeks(vector<int>& milestones) {
        long long sum = 0;
        int max_val = 0;
        for(int miles : milestones) {
            sum += miles;
            max_val = max(max_val , miles);
        } 
        if(max_val > (sum - max_val)) {
            return 2 * (sum - max_val) + 1;
        }
        return sum;
    }
};