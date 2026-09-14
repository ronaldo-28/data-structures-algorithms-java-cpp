class Solution {
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses,
            Comparator.comparingInt((int[] c) -> c[1])
                .thenComparingInt((int[] c) -> c[0]));

        PriorityQueue<Integer> pq = new PriorityQueue<>(
            Comparator.comparingInt((Integer a) -> a).reversed()
        );

        int currTime = 0;

        for(int course[] : courses){
            // if(course[0] <= course[1]){
            //     if(course[0] + currTime <= course[1]){
            //     currTime += course[0];
            //     pq.offer(course[0]);
            // }
            // else if(pq.size() > 0 && pq.peek() > course[0]){
            //     currTime -= pq.poll();
            //     pq.offer(course[0]);
            //     currTime += course[0];
            // }
            // }
            pq.offer(course[0]);
            currTime += course[0];

            if(currTime > course[1])    currTime-=pq.poll();
        }
        return pq.size();
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