
class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        // (index, nextFreeTime)
        PriorityQueue <int[]> busyServers = new PriorityQueue <>((a, b) -> {
            if (a[1] != b[1]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        int max = 1;
        int requestProcessed[] = new int[k];
        for (int i = 0; i < Math.min(k, arrival.length); i++) {
            requestProcessed[i] = 1;
            busyServers.add(new int[]{i, arrival[i] + load[i]});
        }
        TreeSet <Integer> available = new TreeSet <>();
        for (int i = k; i < arrival.length; i++) {
            int arrivalTime = arrival[i];
            while (!busyServers.isEmpty() && busyServers.peek()[1] <= arrivalTime) {
                available.add(busyServers.poll()[0]);
            }
            int serverRequired = i % k;
            if (available.size() == 0) {
                continue;
            }
            int serverGot;
            if (available.ceiling(serverRequired) != null) {
                serverGot = available.ceiling(serverRequired);
            } else {
                serverGot = available.pollFirst();
            }
            available.remove(serverGot);
            requestProcessed[serverGot] ++;
            max = Math.max(max, requestProcessed[serverGot]);
            busyServers.add(new int[]{serverGot, arrivalTime + load[i]});
        }
        List <Integer> res = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            if (requestProcessed[i] == max) {
                res.add(i);
            }
        }
        return res;
    }
}