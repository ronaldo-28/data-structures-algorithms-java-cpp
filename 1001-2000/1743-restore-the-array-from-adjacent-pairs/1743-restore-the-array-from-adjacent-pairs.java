class Solution {
    private static int OFFSET = 100_000;
    public int[] restoreArray(int[][] adjacentPairs) {
        var neighbor1 = new int[200_001];
        var neighbor2 = new int[200_001];
        Arrays.fill(neighbor1, Integer.MIN_VALUE);
        Arrays.fill(neighbor2, Integer.MIN_VALUE);

        for (int[] pair : adjacentPairs) {
            int u = pair[0];
            int uA = u + OFFSET;
            int v = pair[1];
            int vA = v + OFFSET;
            var nei = neighbor1[uA] == Integer.MIN_VALUE ? neighbor1 : neighbor2;
            nei[uA] = v;
            nei = neighbor1[vA] == Integer.MIN_VALUE ? neighbor1 : neighbor2;
            nei[vA] = u;
        }

        var result = new int[adjacentPairs.length + 1];

        var n1 = Integer.MIN_VALUE;
        var n2 = 0;
        for (int[] pair : adjacentPairs) {
            var u = pair[0];
            var v = pair[1];
            if (neighbor2[u + OFFSET] == Integer.MIN_VALUE) {
                n2 = u;
                break;
            }
            if (neighbor2[v + OFFSET] == Integer.MIN_VALUE) {
                n2 = v;
                break;
            }
        }

        result[0] = n2;
        // println("0: $n1, $n2")
        for (int i = 1; i < result.length; i++) {
            var neigh1 = neighbor1[n2 + OFFSET];
            var use2 = neigh1 == n1;
            n1 = n2;
            var nei = use2 ? neighbor2 : neighbor1;
            n2 = nei[n2 + OFFSET];
            result[i] = n2;
            // println("$i: $n1, $n2")
        }
        return result;
    }
}