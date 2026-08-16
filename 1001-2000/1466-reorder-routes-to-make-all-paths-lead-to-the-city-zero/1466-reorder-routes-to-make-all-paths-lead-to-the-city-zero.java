class Solution {
    public int minReorder(int n, int[][] connections) {
        boolean[] canReach = new boolean[n];
        int rows = connections.length;
        canReach[0] = true;
        int count = 0;
        
        int[] s1 = new int[rows];
        int top1 = 0; // Pointer for s1
        int[] s2 = new int[rows];
        int top2 = 0; // Pointer for s2

        // Initial pass to process connections and populate s1
        for (int i = 0; i < rows; i++) {
            int from = connections[i][0];
            int to = connections[i][1];
            if (canReach[from]) {
                count++;
                canReach[to] = true;
            } else if (canReach[to]) {
                canReach[from] = true;
            } else {
                // Push index i onto stack s1
                s1[top1++] = i;
            }
        }

        // Process stacks until both are empty
        while (top1 > 0 || top2 > 0) {
            // Process all elements in s1
            while (top1 > 0) {
                int i = s1[--top1]; // Pop from s1
                int from = connections[i][0];
                int to = connections[i][1];
                
                if (canReach[from]) {
                    count++;
                    canReach[to] = true;
                } else if (canReach[to]) {
                    canReach[from] = true;
                } else {
                    // Push index i onto stack s2
                    s2[top2++] = i;
                }
            }

            // Process all elements in s2
            while (top2 > 0) {
                int i = s2[--top2]; // Pop from s2
                int from = connections[i][0];
                int to = connections[i][1];
                
                if (canReach[from]) {
                    count++;
                    canReach[to] = true;
                } else if (canReach[to]) {
                    canReach[from] = true;
                } else {
                    // Push index i back onto stack s1 for further processing
                    s1[top1++] = i;
                }
            }
        }

        return count;
    }
}