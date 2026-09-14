class Vector2D {
    private int row;
    private int col;
    private int[][] v;

    public Vector2D(int[][] v) {
        this.v = v;
    }
    
    public int next() {
        updateCoors();
        return v[row][col++];
    }
    
    public boolean hasNext() {
        updateCoors();
        return row != v.length;
    }
    
    private void updateCoors() {
        while (row < v.length && col == v[row].length) {
            row++;
            col = 0;
        } 
    }
}