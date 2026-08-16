class Solution {
    public List<Integer> getRow(int n) {
        List<Integer> row = new ArrayList<>();
        n = n+1;
         long ans = 1;
        row.add((int ) ans); // first element is always 1
        
        for (int r = 1; r < n; r++) {
            ans = ans * (n - r );
            ans = ans / r;
            row.add((int) ans);
        }
        
        return row;
    }
}