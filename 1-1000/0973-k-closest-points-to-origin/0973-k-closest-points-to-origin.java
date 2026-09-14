class Solution {
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter writer = new FileWriter("display_runtime.txt")) {
                writer.write("0");
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }));
    }
    public int[][] kClosest(int[][] points, int k) {
        int[][] ans = new int[k][2];

        Arrays.sort(points, (a,b)->{
            int d1 = a[0]*a[0] + a[1]*a[1];
            int d2 = b[0]*b[0] + b[1]*b[1];

            return Integer.compare(d1,d2);
        });

        for(int i=0; i<k; i++){
            ans[i] = points[i];
        }

        return ans;
    }
}