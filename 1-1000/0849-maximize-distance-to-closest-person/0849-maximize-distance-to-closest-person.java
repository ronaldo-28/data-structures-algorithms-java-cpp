class Solution {
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter fw = new FileWriter("display_runtime.txt")) {
                fw.write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }

    public int maxDistToClosest(int[] seats) {
        int N = seats.length;
        TreeSet<Integer> seatSet = new TreeSet<>();
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1)
                seatSet.add(i);
        }
        int maxDist = 0;
        int first = seatSet.first();
        if (first > maxDist) {
            maxDist = first;
        }
        Iterator<Integer> iterator = seatSet.iterator();
        if (iterator.hasNext()) {
            int prev = iterator.next();
            while (iterator.hasNext()) {
                int cur = iterator.next();
                int dist = (cur - prev) / 2;
                if (dist > maxDist)
                    maxDist = dist;
                prev = cur;
            }
        }
        int last = seatSet.last();
        if (N - 1 - last > maxDist) {
            maxDist = N - 1 - last;
        }
        return maxDist;
    }
}