class RangeFreqQuery {

    private final HashMap<Integer, Integer> map;
    private final int[][] positions;
    private final int[] positionsColSize;
    private int positionsSize;
    private final int[] arr;

    public RangeFreqQuery(int[] arr) {
        this.arr = arr;
        map = new HashMap<>(arr.length);
        positionsColSize = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            Integer val = map.get(arr[i]);
            if (val == null) {
                positionsColSize[positionsSize]++;
                map.put(arr[i], positionsSize);
                positionsSize++;
            } else {
                positionsColSize[val]++;
            }
        }

        positions = new int[positionsSize][];

        for (int i = 0; i < positionsSize; i++) {
            positions[i] = new int[positionsColSize[i]];
            positionsColSize[i] = 0;
        }

        for (int i = 0; i < arr.length; i++) {
            Integer val = map.get(arr[i]);
            positions[val][positionsColSize[val]] = i;
            positionsColSize[val]++;
        }
    }

    // Returns the index of the first element >= val
    private int find(int val, int[] arr) {
        if (arr[0] >= val)              return 0;
        if (arr[arr.length - 1] < val)  return arr.length;
        if (arr[arr.length - 1] == val) return arr.length - 1;

        int l = 0;
        int r = arr.length - 1;

        while (l + 1 < r) {
            int m = (l + r) / 2;
            if (arr[m] == val) return m;
            if (arr[m] < val)  l = m;
            else               r = m;
        }

        return r;
    }

    public int query(int left, int right, int value) {
        if (left == right) {
            return arr[left] == value ? 1 : 0;
        }

        Integer val = map.get(value);
        if (val == null) return 0;

        int a = find(left,  positions[val]);
        int b = find(right, positions[val]);

        if (b < positions[val].length && positions[val][b] == right) {
            return b - a + 1;
        }

        return b - a;
    }
}

/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * RangeFreqQuery obj = new RangeFreqQuery(arr);
 * int param_1 = obj.query(left,right,value);
 */