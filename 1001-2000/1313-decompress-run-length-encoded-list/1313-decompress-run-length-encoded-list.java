class Solution {
    public int[] decompressRLElist(int[] nums) {
        // List<Integer> res = new ArrayList<>();
        // int freq;
        // int val;
        // for (int i = 0; i < nums.length; i = i + 2) {
        //     freq = nums[i];
        //     val = nums[i + 1];
        //     for (int j = 0; j < freq; j++) {
        //         res.add(val);
        //     }
        // }
        // int[] resArray = new int[res.size()];
        // for (int i = 0; i < res.size(); i++) resArray[i] = res.get(i);
        // return resArray;

        int arraySize = 0;
        for (int i = 0; i < nums.length; i = i + 2) arraySize += nums[i];
        int[] res = new int[arraySize];
        int index = 0;
        for (int i = 0; i < nums.length; i = i + 2) {
            Arrays.fill(res, index, index + nums[i], nums[i + 1]);
            index += nums[i];
        }
        return res;
    }
}