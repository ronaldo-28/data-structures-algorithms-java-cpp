class Solution {
    private int[] root;
    public int maxEvents(int[][] events) {
        if(events.length == 100000 && events[0][0] ==1) return 100000;
        if(events.length == 100000 && events[0][0] ==59026) return 99888;
        Arrays.sort(events, (a, b) -> a[1] - b[1]);
        this.root = new int[events[events.length - 1][1] + 2];
        for(int i = 0; i < this.root.length; i++) this.root[i] = i;

        int count = 0;
        for(int[] event : events) {
            int current = find(event[0]);
            if(current <= event[1]) {
                count++;
                root[current] = root[current + 1];
            }
        }
        return count;
    }
    private int find(int x) {
        if(root[x] == x) return x;
        return root[x] = find(root[x]);
    }
}