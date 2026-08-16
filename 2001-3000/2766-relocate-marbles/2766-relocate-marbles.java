class Solution {
    public List<Integer> relocateMarbles(int[] nums, int[] moveFrom, int[] moveTo) {
        int m = nums.length, n = moveFrom.length;
        List<Integer> list = new ArrayList<>();

        Map<Integer, Integer> map = new HashMap<>();

        Arrays.sort(nums);
        int[] arr = new int[m];
        int q = 0;
        int prev = -1;
        for (int f : nums) {
            if (f != prev) {
                arr[q++] = f;
            } 

            prev = f;
        } 

        for (int i = n - 1; i >= 0; i--) {
            int a = moveFrom[i], b = moveTo[i];

            int val = map.containsKey(b) ? map.get(b) : b;

            map.put(a, val);
        } 

        for (int i = 0; i < q; i++) {
            int f = arr[i];

            arr[i] = map.containsKey(f) ? map.get(f) : f;
        } 

        Arrays.sort(arr, 0, q);

        prev = -1;
        for (int i = 0; i < q; i++) {
            int f = arr[i];

            if (f != prev) list.add(f);

            prev = f;
        }

        return list;
    }
}