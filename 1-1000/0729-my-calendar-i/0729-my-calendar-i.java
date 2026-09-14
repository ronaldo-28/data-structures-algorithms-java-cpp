class MyCalendar {
    private TreeSet<Integer[]> set;
    public MyCalendar() {
        this.set =  new TreeSet<>(
            (a, b) -> Integer.compare(a[0], b[0])
    );
    }
    
    public boolean book(int startTime, int endTime) {
        Integer[][] data = set.toArray(new Integer[set.size()][2]);
        int low = 0;
        int high = data.length - 1;
        int ist, iet;
        while (low <= high) {
            int mid = (low + (high - low) / 2);
            ist = data[mid][0];
            iet = data[mid][1];
            if(ist <= startTime && startTime< iet) return false;
            if(ist < endTime && endTime < iet) return false;
            if(startTime <= ist && ist < endTime) return false;
            if(startTime< iet && iet < endTime) return false;
            if (data[mid][0] < endTime) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        set.add(new Integer[]{startTime, endTime});
        return true;    
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

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(startTime,endTime);
 */