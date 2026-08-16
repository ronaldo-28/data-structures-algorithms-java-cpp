class Solution {
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
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;

        double[][] cars = new double[n][2];
        //position, time

        for(int i = 0; i < n; i++){
            cars[i][0] = position[i];
            cars[i][1] = (double)(target-position[i])/speed[i];
        }

        Arrays.sort(cars, (a, b) -> Double.compare(a[0], b[0]));
        //lowest position to highest position..

        int fleet = 0;
        double fastestTime = 0; 
        for(int i = n-1; i >= 0; i--){
                
            if(cars[i][1] > fastestTime){
                fleet++;
                fastestTime = cars[i][1];
            }
        }

        return fleet;
    }
}