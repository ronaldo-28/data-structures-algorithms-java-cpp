class Matrix3D {

    private final int[][][] grid;
    private final int[] layerCount;     
    private int maxCount;
    private int maxLayerIndex;      

    public Matrix3D(int n) {
        grid = new int[n][n][n];
        layerCount = new int[n];
        maxCount = 0;
        maxLayerIndex = n - 1;
    }

    public void setCell(int x, int y, int z) {
        if (grid[x][y][z] == 0) {
            grid[x][y][z] = 1;
            layerCount[x]++;

            if (layerCount[x] > maxCount ||
               (layerCount[x] == maxCount && x > maxLayerIndex)) {
                maxCount = layerCount[x];
                maxLayerIndex = x;
            }
        }
    }

    public void unsetCell(int x, int y, int z) {
        if (grid[x][y][z] == 1) {
            grid[x][y][z] = 0;
            layerCount[x]--;

            if (x == maxLayerIndex) {
                recomputeMaxLayer();
            }
        }
    }

    private void recomputeMaxLayer() {
        maxCount = 0;
        int bestIndex = 0;

        for (int i = 0; i < layerCount.length; i++) {
            if (layerCount[i] > maxCount ||
               (layerCount[i] == maxCount && i > bestIndex)) {
                maxCount = layerCount[i];
                bestIndex = i;
            }
        }

        maxLayerIndex = bestIndex;
    }

    public int largestMatrix() {
        return maxLayerIndex;
    }
}

/**
 * Your Matrix3D object will be instantiated and called as such:
 * Matrix3D obj = new Matrix3D(n);
 * obj.setCell(x,y,z);
 * obj.unsetCell(x,y,z);
 * int param_3 = obj.largestMatrix();
 */