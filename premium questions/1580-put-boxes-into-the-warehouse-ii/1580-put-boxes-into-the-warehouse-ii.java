class Solution {
    public int maxBoxesInWarehouse(int[] boxes, int[] warehouse) {
        int l = 0, r = warehouse.length - 1;
        Arrays.sort(boxes);
        int b = boxes.length - 1, res = 0;
        while (l <= r) {
            while (b >= 0 && warehouse[l] < boxes[b] && warehouse[r] < boxes[b]) b--;

            if (b == -1) return res;

            if (warehouse[l] >= boxes[b] && warehouse[r] >= boxes[b]) {
                if (warehouse[l] <= warehouse[r]) l++;
                else r--;
            } else if (warehouse[l] >= boxes[b]) {
                l++;
            } else {
                r--;
            }

            b--;
            res++;
        }
        return res;
    }
}