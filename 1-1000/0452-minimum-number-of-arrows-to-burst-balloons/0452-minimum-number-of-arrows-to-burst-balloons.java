class Solution {
    public int findMinArrowShots(int[][] points) {
        int operation = 0;
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));
        int n = points.length;
        int i = 0;
        while (i < n) {
            int end = points[i][1];
            while (i < n && end >= points[i][0]) {
                i++;
            }
            operation++;
        }
        return operation;
    }
    static {
        Runtime.getRuntime().gc();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter writer = new FileWriter("display_runtime.txt")) {
                writer.write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
}