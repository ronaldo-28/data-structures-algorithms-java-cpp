/*
By treating every 0 in the array as a -1, the problem transforms: we are now looking for the total number of subarrays whose sum is strictly greater than zero.

If we calculate a running prefix sum, a subarray from index j to i has a positive sum if and only if S_i - S_{j-1} > 0, which simplifies to S_i > S_{j-1}. Therefore, at every index i, we need to count how many previous prefix sums were strictly less than our current prefix sum.

The $O(N)$ Optimization
A standard way to count previous values that are smaller than a current value is using a Fenwick Tree (Binary Indexed Tree), which takes O(N log N) time. However, because our prefix sum only ever changes by exactly +1 or -1 at each step, we can solve this in $O(N)$ time.

We can maintain an array to store the frequency of each prefix sum we've seen, alongside a running valid_count that tracks how many previous prefix sums are strictly less than our current one.

When we see a 1: The prefix sum increases by 1. All the previous prefix sums that were equal to our old sum are now strictly less than our new sum. We add their frequency to valid_count.

When we see a 0: The prefix sum decreases by 1. All the previous prefix sums that were equal to our new sum were previously strictly less than our old sum. Since our sum dropped, they are no longer smaller (they are now equal), so we subtract their frequency from valid_count.
*/

#include <vector>

using namespace std;

class Solution {
public:
    int subarraysWithMoreOnesThanZeroes(vector<int>& nums) {
        int n = nums.size();
        int MOD = 1e9 + 7;
        
        // The prefix sum can range from -n (all zeros) to +n (all ones).
        // To use an array for O(1) lookups instead of a map, we need a size of 2*n + 1.
        // We use an offset of 'n' to handle negative prefix sums safely.
        vector<int> freq(2 * n + 1, 0);
        int offset = n;
        
        // The empty prefix sum before starting is 0
        freq[0 + offset] = 1;
        
        int prefix_sum = 0;
        long long valid_count = 0; // Number of previous prefix sums < current prefix sum
        long long total_subarrays = 0;
        
        for (int num : nums) {
            if (num == 1) {
                // Prefix sum will increase. 
                // The old prefix sum is now strictly less than the new one.
                valid_count += freq[prefix_sum + offset];
                prefix_sum++;
            } else {
                // Prefix sum will decrease.
                // The new prefix sum is no longer strictly less, it is now equal.
                prefix_sum--;
                valid_count -= freq[prefix_sum + offset];
            }
            
            // Add the valid subarrays ending at this index to our total
            total_subarrays = (total_subarrays + valid_count) % MOD;
            
            // Record this new prefix sum for future iterations
            freq[prefix_sum + offset]++;
        }
        
        return total_subarrays;
    }
};

/*
Time Complexity: O(N). We make a single pass through the array, and performing the array lookups and updates takes O(1) time per element.

Space Complexity: O(N). We allocate a freq array of size 2N+1 to cover all possible prefix sum values. This avoids the overhead of a hash map while guaranteeing constant-time frequency lookups.
*/

/* BIT solution: need to understand
class Solution {
    struct BIT {
        int n;
        vector<int> bit;
        BIT(int n) {
            this -> n = n;
            bit.assign(n+1,0);
        }
        void increment(int i, int val) {
            for(; i <= n; i += i & -i) {
                bit[i] += val;
            }
        }
        int query(int i) {
            int ans = 0;
            for(;i > 0; i -= i & -i) {
                ans += bit[i];
            }
            return ans;
        }
    };
public:
    int subarraysWithMoreOnesThanZeroes(vector<int>& nums) {
        // int ans = 0;
        // unordered_map<int,int> mp;
        // mp[0] = 1;
        // int validsubarrays = 0, currsum = 0;
        // for(auto n:nums) {
        //     if(n == 1) {
        //         validsubarrays += mp[currsum];
        //         currsum++;
        //     }
        //     else {
        //         currsum--;
        //         validsubarrays -= mp[currsum];
        //     }
        //     ans = (ans + validsubarrays) % 1000000007;
        //     mp[currsum]++;
        // }
        // return ans;

        int ans = 0;
        int n = nums.size();
        int MOD = 1000000007;
        BIT bit(2*n + 1);
        bit.increment(n + 1,1);
        int currsum = 0;
        for(auto num:nums) {
            currsum += (num == 1) ? 1 : -1;
            ans = (ans + bit.query(currsum + n)) % MOD;
            bit.increment(currsum + n + 1, 1);
        }
        return ans;
    }
};
*/