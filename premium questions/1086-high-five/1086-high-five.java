class Solution {
    public int[][] highFive(int[][] items) {        
        PriorityQueue<Integer> []map = new PriorityQueue[1001];
        int count = 0;
        for (int []item : items) {
            int index = item[0];
            int val = item[1];
            if (map[index] == null) {
                map[index] = new PriorityQueue<>((val1, val2) -> val1 - val2);                
                count ++;
            }
            map[index].offer(val);
            if (map[index].size() > 5) {
                map[index].poll();
            }
        }
        int [][]result = new int[count][2];
        int idx = 0;
        for (int i = 1; i <= 1000; i ++) {
            if (map[i] != null) {
                PriorityQueue<Integer> queue = map[i];
                int sum = 0;                
                while (!queue.isEmpty()) {
                    sum += queue.poll();
                }
                result[idx][1] = (sum / 5);
                result[idx][0] = i;
                idx++;
            }
        }
        return result;
    }
}