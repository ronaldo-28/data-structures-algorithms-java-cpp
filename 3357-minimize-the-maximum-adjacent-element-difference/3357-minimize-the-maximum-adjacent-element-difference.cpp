class Solution {
public:
    using ll = long long;

    pair<ll, ll> getRange(ll val, ll mid){
        return {max(1LL, val - mid), val + mid};
    }

    pair<ll, ll> intersect(const pair<ll, ll>& a, const pair<ll, ll>& b){
        return {max(a.first, b.first), min(a.second, b.second)};
    }
    
    bool cover(vector<pair<ll, ll>>& ranges, ll mid, bool close){
        if(ranges.empty()) return true;

        sort(begin(ranges), end(ranges), [](auto& a, auto& b){
            return a.second < b.second;
        });

        ll x = ranges[0].second;
        
        // left and right are ranges of y 
        ll left = 1;
        ll right = 1e18;

        for(auto& [l, r] : ranges){
            if(l <= x && x <= r) continue;

            // if x cannot cover this range then y has to cover this
            left = max(left, l);
            right = min(right, r);
        }

        if(close){
            // if x and y are coming adjacent then abs(x - y) <= mid
            left = max(left, x - mid);
            right = min(right, x + mid);
        }

        return left <= right;
    }

    bool f(int mid, vector<int>& nums){
        int n = nums.size();

        vector<pair<ll, ll>> closeRanges;
        vector<pair<ll, ll>> farRanges;

        bool farPossible = true;

        for(int i = 0; i < n; ){
            if(nums[i] != -1){
                if(i + 1 < n && nums[i + 1]  != -1){
                    if(abs((ll)nums[i] - nums[i + 1]) > mid) return false;
                }

                i++;
                continue;
            }

            int start = i;

            while(i < n && nums[i] == -1) ++i;

            int end = i - 1;

            // if all elements are -1 then we can make all same
            if(start == 0 && i == n) continue;

            // this means -1 block has both left and right neighbour
            if(start >= 1 && i < n){
                auto leftRange = getRange(nums[start - 1], mid);
                auto rightRange = getRange(nums[i], mid);

                if(end == start){
                    // only one -1 so one value should satisfy both neighbour
                    auto both = intersect(leftRange, rightRange);

                    if(both.first > both.second) return false;

                    closeRanges.push_back(both);
                    farRanges.push_back(both);
                }
                else{
                    // multiple -1
                    // if x and y are close then one can satisfy left side
                    // and other can satisfy right side
                    closeRanges.push_back(leftRange);
                    closeRanges.push_back(rightRange);

                    // if x and y are far then we cannot put them adjacent
                    // so one value has to satisfy both neighbour
                    auto both = intersect(leftRange, rightRange);

                    if(both.first > both.second) farPossible = false;
                    else farRanges.push_back(both);
                }
            }
            else{
                // -1 block has only one neighbour
                int neigh = (start != 0) ? nums[start - 1] : nums[i];
                auto range = getRange(neigh, mid);

                closeRanges.push_back(range);
                farRanges.push_back(range);
            }
        }

        bool close = cover(closeRanges, mid, true);
        bool far = farPossible && cover(farRanges, mid, false);

        return close || far;
    }

    int minDifference(vector<int>& nums) {
        int n = nums.size();

        int low = 0;
        int high = 1e9;
        int ans = high;

        while(low <= high){
            int mid = low + (high - low) / 2;

            if(f(mid, nums)){
                ans = mid;
                high = mid - 1;
            }
            else low = mid + 1;
        }

        return ans;
    }
};