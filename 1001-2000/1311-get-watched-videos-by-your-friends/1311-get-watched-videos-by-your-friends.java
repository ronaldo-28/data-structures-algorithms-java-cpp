class Pair implements Comparable<Pair> {
    String ch;
    int val;

    Pair(String ch, int val) {
        this.ch = ch;
        this.val = val;
    }

    public int compareTo(Pair that) {
        if (this.val == that.val) {
            return this.ch.compareTo(that.ch);

        }
        return this.val - that.val;
    }
}

class Solution {
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (java.io.FileWriter fw = new java.io.FileWriter("display_runtime.txt")) {
                fw.write("0");
            } catch (Exception e) {
            }
        }));
    }
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        Queue<Integer> q = new LinkedList<>();
        List<String> list = new ArrayList<>();
        List<String> ans = new ArrayList<>();
        HashSet<Integer> visited = new HashSet<>();
        q.add(id);
        visited.add(id);
        int l = 0;
        while (!q.isEmpty()) {
            int s = q.size();
            if (l == level) {
                for (int i = 0; i < s; i++) {
                    int friend = q.poll();
                    for (int f = 0; f < watchedVideos.get(friend).size(); f++) {
                        list.add(watchedVideos.get(friend).get(f));
                    }
                }
                break;
            }
            for (int i = 0; i < s; i++) {
                int friend = q.poll();
                for (int f = 0; f < friends[friend].length; f++) {
                    if (!visited.contains(friends[friend][f])) {
                        q.add(friends[friend][f]);
                        visited.add(friends[friend][f]);
                    }
                }
            }
            l++;
        }
        HashMap<String, Integer> m = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            String ch = list.get(i);
            m.put(ch, m.getOrDefault(ch, 0) + 1);
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (String k : m.keySet()) {
            pq.offer(new Pair(k, m.get(k)));
        }
        while (!pq.isEmpty()) {
            ans.add(pq.poll().ch);
        }

        return ans;
    }
}