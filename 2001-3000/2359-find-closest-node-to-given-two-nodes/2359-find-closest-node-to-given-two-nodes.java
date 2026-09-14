class Solution {
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        if (node1 == node2 ) return node1;
        boolean[] arr1 = new boolean[edges.length];
        boolean[] arr2 = new boolean[edges.length];
        arr1[node1] = true;
        arr2[node2] = true;
        int ans = edges.length;
        while (node1 != -1 || node2 != -1) {
            if (node1 != -1 ) {
                int next1 = edges[node1];
                // System.out.print("1=" + next1 + ", ");

                if (next1 != -1 && arr1[next1]) next1 = -1;
                if (next1 != -1 ) {
                    if (arr2[next1]) ans = next1;
                    arr1[next1] = true;
                }
                node1 = next1;
            }
           if (node2 != -1) {
                int next2 = edges[node2];
                // System.out.print("2=" + next2 + ", ");

                if (next2 != -1 && arr2[next2]) next2 = -1;
                if (next2 != -1) {
                    if (arr1[next2]) ans = Math.min(ans, next2);
                    arr2[next2] = true;
                }
                node2 = next2;
            }
            if (ans != edges.length) return ans;
            // System.out.println(node1 + " " + node2);
        }

        return -1;
    }

}