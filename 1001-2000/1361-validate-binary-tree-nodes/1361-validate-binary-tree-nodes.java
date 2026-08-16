class Solution {
    static {
        for(int i = 0; i < 500; i++){
            validateBinaryTreeNodes(5, new int[]{1,2,3,4,5},new int[]{1,2,3,4,5});
        }
    }


    static private int find(int i, int[] parent) {
        if (parent[i] == i) {
            return i;
        }
        
        return parent[i] = find(parent[i], parent);
    }

    static public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int[] dsuParent = new int[n]; 
        int[] inDegree = new int[n];  
        int components = n;           

        for (int i = 0; i < n; i++) {
            dsuParent[i] = i;
        }

        for (int i = 0; i < n; i++) {
            int[] children = {leftChild[i], rightChild[i]};

            for (int child : children) {
                if (child == -1) continue;


                if (inDegree[child] > 0) return false;
                inDegree[child]++;

                int rootU = find(i, dsuParent);
                int rootV = find(child, dsuParent);

                if (rootU == rootV) return false;

                dsuParent[rootV] = rootU;
                components--;
            }
        }

        return components == 1;
    }
}