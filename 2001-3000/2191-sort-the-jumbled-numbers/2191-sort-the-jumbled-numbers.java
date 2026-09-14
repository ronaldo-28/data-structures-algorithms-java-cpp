class Solution {
    private int[] mapping, nums, map, mapn;

    public int[] sortJumbled(int[] mapping, int[] nums) {
        this.mapping = mapping;
        this.nums = nums;
        int len = nums.length;
        map = new int[len];
        mapn = new int[len];
        for (int i = 0; i < len; i++) {
            map[i] = val(nums[i]);
            mapn[i]=i;
        }
        sort(0, len - 1,map);
        int bI=0;
        while (bI<len-1){
            int eI=bI;
            while ((eI<len-1)&&(map[bI]==map[eI+1]))
                eI++;
            if (eI>bI)
                sort(bI,eI,mapn);
            bI=eI+1;
        }
        return nums;
    }

    int val(int num) {
        int mul = 1, res = 0;
        do {
            int v = mapping[num % 10];
            res += v * mul;
            mul *= 10;
            num /= 10;
        } while (num > 0);
        return res;
    }

    void swap(int i1, int i2) {
        int tmp = nums[i1];
        int tmp2 = map[i1];
        int tmp3 = mapn[i1];
        nums[i1] = nums[i2];
        nums[i2] = tmp;
        map[i1] = map[i2];
        map[i2] = tmp2;
        mapn[i1] = mapn[i2];
        mapn[i2] = tmp3;
    }

    private void sort(int bI, int eI, int[]map) {
        if (eI - 1 > bI) {
            if (map[bI] == map[eI]) {
                for (int i = bI + 1; i <= eI - 1; i++) {
                    if (map[i] > map[bI]) {
                        swap(i, eI);
                        sort(bI, eI, map);
                        break;
                    }
                    if (map[i] < map[bI]) {
                        swap(bI, i);
                        sort(bI, eI,map);
                        break;
                    }
                }
                return;
            }
            int middle = (map[eI] + map[bI]) >> 1;
            int bi = bI, ei = eI;
            while (ei > bi) {
                while (map[bi] <= middle)
                    bi++;
                while (map[ei] > middle)
                    ei--;
                if (bi > ei) {
                    sort(bI, ei,map);
                    sort(bi, eI,map);
                    return;
                }
                swap(bi, ei);
            }
        }
        if (map[bI] > map[eI])
            swap(bI, eI);
    }
}