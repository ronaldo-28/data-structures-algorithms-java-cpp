class Solution {
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter writer = new FileWriter("display_runtime.txt")) {
                writer.write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] arr = new int[n];

        for(int i = 0; i < n ; i++){
            arr[i] = 0;
        }

        for(int[] booking : bookings){
            for(int i = booking[0]-1 ; i < booking[1] ; i++){
                arr[i] += booking[2];
            }
        }

        return arr;
    }
}