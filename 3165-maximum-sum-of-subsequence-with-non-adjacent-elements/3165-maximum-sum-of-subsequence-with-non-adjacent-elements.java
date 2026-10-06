class Solution {
    Node[] segments;
    long[] arr;
    long mod = 1000000007L;

    public int maximumSumSubsequence(int[] nums, int[][] queries) {
        this.arr = new long[nums.length];

        for (int i = 0; i < nums.length; i++) {
            arr[i] = nums[i];
        }

        this.segments = new Node[4 * arr.length];

        build(0, 0, arr.length - 1);

        long ans = 0;

        for (int[] q : queries) {
            query(0, 0, arr.length - 1, q[0], q[1]);
            ans = (ans + segments[0].m) % mod;
        }

        return (int) ans;
    }

    private Node build(int nodeIndex, int s, int e) {

        if (s == e) {
            Node node = new Node(arr[s]);
            segments[nodeIndex] = node;
            return node;
        }

        int m = s + (e - s) / 2;

        Node left = build(nodeIndex * 2 + 1, s, m);
        Node right = build(nodeIndex * 2 + 2, m + 1, e);

        long max = Math.max(
            left.wr + right.m,
            left.m + right.wl
        );

        Node node = new Node(max);

        node.wl = Math.max(
            left.wlr + right.m,
            left.wl + right.wl
        );

        node.wr = Math.max(
            right.wlr + left.m,
            right.wr + left.wr
        );

        node.wlr = Math.max(
            left.wlr + right.wr,
            left.wl + right.wlr
        );

        segments[nodeIndex] = node;

        return node;
    }

    private void query(int nodeIndex, int s, int e, int ti, long tc) {

        Node node = segments[nodeIndex];

        if (s == e && ti == s) {
            arr[ti] = tc;
            node.m = Math.max(0L, tc);
            return;
        }

        int m = s + (e - s) / 2;

        if (ti <= m)
            query(nodeIndex * 2 + 1, s, m, ti, tc);
        else
            query(nodeIndex * 2 + 2, m + 1, e, ti, tc);

        Node left = segments[nodeIndex * 2 + 1];
        Node right = segments[nodeIndex * 2 + 2];

        long max = Math.max(
            left.wr + right.m,
            left.m + right.wl
        );

        node.m = max;

        node.wl = Math.max(
            left.wlr + right.m,
            left.wl + right.wl
        );

        node.wr = Math.max(
            right.wlr + left.m,
            right.wr + left.wr
        );

        node.wlr = Math.max(
            left.wlr + right.wr,
            left.wl + right.wlr
        );
    }
}


class Node {
    long wl;
    long wr;
    long m;
    long wlr;

    Node(long ele) {
        this.m = ele > 0 ? ele : 0;
        this.wl = 0;
        this.wr = 0;
        this.wlr = 0;
    }
}