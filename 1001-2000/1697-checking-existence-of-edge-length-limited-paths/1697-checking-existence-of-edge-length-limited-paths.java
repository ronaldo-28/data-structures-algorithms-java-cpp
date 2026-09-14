class Solution {
    // These MASK and SHIFT values are used to build sort 
    // arrays for the edges and for the queries.  The arrays 
    // to be sorted contain the higher binary digits, and 
    // the index into the edges or the queries into the 
    // lower binary digits.  After sorting, only the index 
    // in the lower part of the long value is used.
    static final long MASK = 0xFFFFFL;
    static final int SHIFT = 20;
    
    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, 
                                               int[][] queries) {
        int eCount = edgeList.length;
        int qCount = queries.length;

        // Sort the edges by distance.
        long[] eSort = new long[eCount];
        for (int i = eCount - 1; i >= 0; i--)
            eSort[i] = ((long)edgeList[i][2] << SHIFT) + i;
        Arrays.sort(eSort);
        
        // Sort the queries by limit distance.
        long[] qSort = new long[qCount];
        for (int i = qCount - 1; i >= 0; i--)
            qSort[i] = ((long)queries[i][2] << SHIFT) + i;
        Arrays.sort(qSort);
        
        // Build the parents array for the union find.
        int[] parents = new int[n];
        for (int i = n - 1; i >= 0; i--)
            parents[i] = i;
        
        boolean[] result = new boolean[qCount];
        int eSortIdx = 0;
        int[] e = edgeList[(int)(eSort[0] & MASK)];
        int eDist = e[2];
        // Loop through all of the queries, in increasing limit distance 
        // order.
        for (int qSortIdx = 0; qSortIdx < qCount; qSortIdx++) {
            int qIdx = (int)(qSort[qSortIdx] & MASK);
            int[] q = queries[qIdx];
            int qDist = q[2];
            // Process all of the unprocessed edges that are within the 
            // current query's limit distance.  Because queries are processed 
            // in low to high distance order, and edges are processed in low 
            // to high distance order, we just process edged (i.e. connect 
            // via union-find) until we hit an edge with larger or same 
            // distance as the current query's limit distance.
            if (eSortIdx < eCount) {
                while (qDist > eDist) {
                    int rootU = findRoot(parents, e[0]);
                    int rootV = findRoot(parents, e[1]);
                    if (rootU != rootV)
                        parents[rootV] = rootU;
                    eSortIdx++;
                    if (eSortIdx >= eCount)  break;
                    e = edgeList[(int)(eSort[eSortIdx] & MASK)];
                    eDist = e[2];
                }
            }
            // Use union-find roots to decide if the two query nodes 
            // are connected by some path.
            if (findRoot(parents, q[0]) == findRoot(parents, q[1])) 
                result[qIdx] = true;
        }
        return result;
    }
    
    
    private int findRoot(int[] parents, int node) {
        if (parents[node] == node)  return node;
        return parents[node] = findRoot(parents, parents[node]);
    }
}