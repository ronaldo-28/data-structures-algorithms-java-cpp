class Solution {
    // 5 4 3
    // 2 1 0
    static int[][] nthDigit = new int[][]{
        {1, 3},     // 0
        {2, 4, 0},  // 1
        {5, 1},
        {4, 0},
        {5, 1, 3},  // 4
        {2, 4}      // 5
    };

    static int[] power = {
        1,
        10,
        100,
        1_000,
        10_000,
        100_000
    };
    static Map<Integer, Integer> visited = new HashMap<Integer, Integer>();

    static {
        init();
    }

    public int slidingPuzzle(int[][] board) {
        var target = toInt(board);
        return visited.getOrDefault(target, -1);
    }

    static int toInt(int[][] b) {
        return b[0][0]*1_00_000
             + b[0][1]* 1_0_000
             + b[0][2]*   1_000
             + b[1][0]*     100
             + b[1][1]*      10
             + b[1][2];
    }

    static void init() {
        var queue = new LinkedList<Node>();

        queue.add(new Node(123450, 0, 0));
        visited.put(123450, 0);

        while(!queue.isEmpty()) {
            var node = queue.poll();
            for(var nextZero : nthDigit[node.zero()]) {
                var value = node.nextValue(nextZero);
                if(visited.containsKey(value)) continue;

                visited.put(value, node.cost() + 1);
                queue.add(new Node(value, nextZero, node.cost() + 1));
            }
        }
    }

    record Node(int value, int zero, int cost) {
        int nextValue(int nextZero) {
            int digit = (value / power[nextZero]) % 10;
            return value + digit * (power[zero] - power[nextZero]);
        }
    }
}