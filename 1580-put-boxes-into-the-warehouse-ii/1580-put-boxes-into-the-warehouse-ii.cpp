class Solution {
public:
    int maxBoxesInWarehouse(vector<int>& boxes, vector<int>& warehouse) {
        // insert box from both sides.
        int m = boxes.size(), n = warehouse.size();
        sort(boxes.begin(), boxes.end());

        int left = 0, right = n - 1, count = 0, boxIndex = m - 1;
        while (left <= right && boxIndex >= 0) {
            if (boxes[boxIndex] <= warehouse[left]) {
                ++ left;
                ++ count;
            } else if (boxes[boxIndex] <= warehouse[right]) {
                -- right;
                ++ count;
            }
            -- boxIndex;
        }
        return count;
    }
};