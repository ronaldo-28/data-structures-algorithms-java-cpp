class Solution {
public:
    vector<int> sortTransformedArray(vector<int>& nums, int a, int b, int c) {
        int n = nums.size();
        vector<int> result(n);
        int left = 0, right = n - 1;
        int index = (a >= 0) ? n - 1 : 0; // Start filling from the end if a > 0

        while (left <= right) {
            int leftVal = transform(nums[left], a, b, c);
            int rightVal = transform(nums[right], a, b, c);

            if (a >= 0) {
                // Parabola opens upwards: larger values are at the ends
                if (leftVal >= rightVal) {
                    result[index--] = leftVal;
                    left++;
                } else {
                    result[index--] = rightVal;
                    right--;
                }
            } else {
                // Parabola opens downwards: smaller values are at the ends
                if (leftVal <= rightVal) {
                    result[index++] = leftVal;
                    left++;
                } else {
                    result[index++] = rightVal;
                    right--;
                }
            }
        }

        return result;
    }

private:
    int transform(int x, int a, int b, int c) {
        return a * x * x + b * x + c;
    }
};