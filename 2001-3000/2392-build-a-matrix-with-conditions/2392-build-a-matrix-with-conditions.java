class Solution {
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        int[] rowIndexes = new int[k + 1];
        if (!doRowCol(rowConditions, rowIndexes))  return new int[0][0];
        int[] colIndexes = new int[k + 1];
        if (!doRowCol(colConditions, colIndexes))  return new int[0][0];
        int[][] result = new int[k][k];
        for (int i = 1; i <= k; i++) 
            result[rowIndexes[i]][colIndexes[i]] = i;
        return result;
    }
    private boolean doRowCol(int[][] conditions, int[] indexes) {
        int k = indexes.length - 1;
        final int FROM_NODE = 0;
        final int TO_NODE = 1;
        int[][] outLists = new int[k + 1][];
        int[] outCounts = new int[k + 1];
        int[] inCounts = new int[k + 1];
        for (int[] edge : conditions) {
            outCounts[edge[FROM_NODE]]++;
            inCounts[edge[TO_NODE]]++;
        }
        for (int i = k; i > 0; i--) 
            outLists[i] = new int[outCounts[i]];
        for (int[] edge : conditions) 
            outLists[edge[FROM_NODE]][--outCounts[edge[FROM_NODE]]] = edge[TO_NODE];
        int totalCounts = conditions.length;
        int indexesNext = 0;
        int[] que = new int[k];
        int queInn = 0;
        int queOut = 0;
        for (int i = 1; i <= k; i++) 
            if (inCounts[i] <= 0)
                que[queInn++] = i;
        if (queInn == queOut)
            return false;   
        int level = 0;
        while (queOut < queInn) {
            for (int remainingAtLevel = queInn - queOut; remainingAtLevel > 0; 
                                remainingAtLevel--) {
                int node = que[queOut++];
                indexes[node] = indexesNext++;
                for (int toNode : outLists[node]) {
                    totalCounts--;
                    if (--inCounts[toNode] <= 0) 
                        que[queInn++] = toNode;
                }
            }
            level++;
        }
        if (totalCounts != 0)  return false;
        return true;
    }
}