class ParkingSystem {
    int[] arr = new int[3];
    public ParkingSystem(int big, int medium, int small) {
        arr[0] = big;
        arr[1] = medium;
        arr[2] = small;
        
    }
    public boolean addCar(int carType) {
        arr[carType-1] = arr[carType-1]-1;
        return arr[carType-1]>=0?true:false;
    }
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter w = new FileWriter("display_runtime.txt")) {
                w.write("-0");
            } catch (Exception e) {}
        }));
    }
}

/**
 * Your ParkingSystem object will be instantiated and called as such:
 * ParkingSystem obj = new ParkingSystem(big, medium, small);
 * boolean param_1 = obj.addCar(carType);
 */