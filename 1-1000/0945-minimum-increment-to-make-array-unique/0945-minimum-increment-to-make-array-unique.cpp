class Solution {
public:
    int minIncrementForUnique(vector<int>& nums) {
        int max_val = 0;
        int size = nums.size();
        for(int num:nums){
            max_val = max(max_val,num); 
        }
        vector<int>count(size+max_val,0);
        long long int answer =0;
        //int max_min_num = 100005; //use after all min_heap elements are used;
        //priority_queue<int,vector<int>,greater<int> > min_heap;
        //this min_heap in not required for more optimizations;

        for(int i=0;i<nums.size();i++){
           count[nums[i]]++;
        }
        for(int i=0;i<count.size();i++){
            if(count[i]<=1){
                continue;
            }
            int dup = count[i]-1;
            count[i+1]+=dup;
            count[i]=1;
            answer+=dup;
        }
        return answer;
    }
};