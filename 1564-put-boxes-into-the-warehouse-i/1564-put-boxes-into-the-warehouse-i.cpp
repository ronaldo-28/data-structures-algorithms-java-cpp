class Solution {
public:
    int maxBoxesInWarehouse(vector<int>& boxes, vector<int>& warehouse) {
        sort(boxes.begin(),boxes.end());

        reverse(boxes.begin(),boxes.end());

        int i = 0, j = 0, count = 0;

        while (i < boxes.size() && j < warehouse.size()){
            if (boxes[i] <= warehouse[j]){
                j += 1;
                count += 1;
            }
            i += 1;
        }

        return count;
    }
};