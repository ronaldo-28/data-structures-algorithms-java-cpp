class Solution {

    private static final int COORD_LIMIT = 7;
    private static final int LAYER = COORD_LIMIT * COORD_LIMIT;
    private static final int POINT_COUNT = COORD_LIMIT * LAYER;
    private static final int[] MID = buildMid();

    public int minGenerations(int[][] points, int[] target) {
        int targetCode = encode(target), size = 0;
        int[] q = new int[POINT_COUNT];
        long[] visited = new long[(POINT_COUNT + 64 - 1) >> 6];
        for (int[] point : points) {
            int curr = encode(point);
            if (curr == targetCode) return 0;
            if (isVisited(visited, curr)) continue;
            visit(visited, curr);
            q[size++] = curr;
        }
        int prev = 0, k = 0;
        while (true) {
            ++k;
            int currSize = size;
            for (int i = 0; i < currSize; ++i) {
                int start = i + 1 > prev ? i + 1 : prev;
                int row = q[i] * POINT_COUNT;
                for (int j = start; j < currSize; ++j) {
                    int mid = MID[row + q[j]];
                    if (isVisited(visited, mid)) continue;
                    if (mid == targetCode) return k;
                    visit(visited, mid);
                    q[size++] = mid;
                }
            }
            if (size == currSize) return -1;
            prev = currSize;
        }
    }

    private static int[] buildMid() {
        int[] mid = new int[POINT_COUNT * POINT_COUNT];
        for (int a = 0; a < POINT_COUNT; ++a) {
            int row = a * POINT_COUNT;
            for (int b = 0; b < POINT_COUNT; ++b) mid[row + b] = midpoint(a, b);
        }
        return mid;
    }

    private static int midpoint(int a, int b) {
        int x1 = a / LAYER, y1 = a / COORD_LIMIT % COORD_LIMIT, z1 = a % COORD_LIMIT;
        int x2 = b / LAYER, y2 = b / COORD_LIMIT % COORD_LIMIT, z2 = b % COORD_LIMIT;
        return encode((x1 + x2) >> 1, (y1 + y2) >> 1, (z1 + z2) >> 1);
    }

    private static int encode(int[] point) {
        return encode(point[0], point[1], point[2]);
    }

    private static int encode(int x, int y, int z) {
        return x * LAYER + y * COORD_LIMIT + z;
    }

    private static boolean isVisited(long[] visited, int point) {
        return (visited[point >>> 6] & (1L << (point & 63))) != 0;
    }

    private static void visit(long[] visited, int point) {
        visited[point >>> 6] |= 1L << (point & 63);
    }
}