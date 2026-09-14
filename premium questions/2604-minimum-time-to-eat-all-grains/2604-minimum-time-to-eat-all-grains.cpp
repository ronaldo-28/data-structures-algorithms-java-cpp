class Solution {
public:

    bool canEatInThisTime(vector<int>& hens,
                          vector<int>& grains,
                          int time) {

        int j = 0;
        int m = grains.size();

        for (int hen : hens) {

            if (j == m)
                return true;

            int grain = grains[j];

            // First remaining grain is to the left of the hen
            if (grain <= hen) {

                int d = hen - grain;

                // Can't even reach the first grain
                if (d > time)
                    return false;

                // Eat all grains up to hen
                while (j < m && grains[j] <= hen)
                    j++;

                // Now try to eat grains to the right of hen.
                //
                // Cost to cover [grain ... right]:
                // min(
                //     go left first then right,
                //     go right first then left
                // ) + ...
                //
                // Equivalent condition:
                // min(d, right - hen) + (right - grain) <= time

                while (j < m) {

                    int right = grains[j];

                    if (min(d, right - hen) + (right - grain) <= time)
                        j++;
                    else
                        break;
                }
            }

            // First remaining grain is to the right
            else {

                while (j < m &&
                       grains[j] - hen <= time) {
                    j++;
                }
            }
        }

        return j == m;
    }

    int minimumTime(vector<int>& hens, vector<int>& grains) {

        sort(hens.begin(), hens.end());
        sort(grains.begin(), grains.end());

        long long int low = 0;

        // Safe upper bound
        long long int high = 10e10;

        long long int ans = high;

        while (low <= high) {

            long long int mid = low + (high - low) / 2;

            if (canEatInThisTime(hens, grains, mid)) {
                ans = mid;
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }

        return ans;
    }
};