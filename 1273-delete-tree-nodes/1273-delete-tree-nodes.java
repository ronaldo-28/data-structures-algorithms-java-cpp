class Solution {
    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
        
        // will eventually hold the size of the subtree rooted at a certain node
        int[] subTreeSize = new int[nodes];
        Arrays.fill(subTreeSize, 1);
        
        // number of child nodes to be processed
        int[] remChildren = new int[nodes];
        for (int p : parent) {
            if (p >= 0) {
                remChildren[p]++;
            }
        }
        
        // does not have to be stack, could be any outher dynamic data structure
        int[] stack = new int[nodes];
        int l = 0;
        
        // start with leaves
        for (int i = 0; i < nodes; i++) {
            if (remChildren[i] == 0) {
                stack[l++] = i;
            }
        }
        
        while (l > 0) {
            
            int node = stack[--l];
            
            // value[node] at this point holds the sum of values of the entire subtree
            if (value[node] == 0) {
                nodes -= subTreeSize[node];
                subTreeSize[node] = 0;
                value[node] = 0;
            } 
            
            int p = parent[node];
            
            if (p >= 0) {
                subTreeSize[p] += subTreeSize[node];
                value[p] += value[node];
                remChildren[p]--;
                // continue with parent if there are no children left to be processed
                if (remChildren[p] == 0) {
                    stack[l++] = p;
                }
            } 
        }
        
        return nodes;
    }
    
}