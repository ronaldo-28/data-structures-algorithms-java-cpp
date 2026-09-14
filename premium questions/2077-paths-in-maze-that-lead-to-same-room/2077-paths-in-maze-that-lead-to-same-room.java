class Solution {
    public int numberOfPaths(int n, int[][] corridors) {
        Map<Integer, BitSet> adj = new HashMap<>();
        var result = 0;
        for (int[] coor : corridors) {
            adj.putIfAbsent(coor[0], new BitSet());
            adj.putIfAbsent(coor[1], new BitSet());
            adj.get(coor[0]).set(coor[1]);
            adj.get(coor[1]).set(coor[0]);
            // clunky Java crap :(
            BitSet clone = (BitSet) adj.get(coor[0]).clone();
            // yes, "and()" method  is mutating the object, can you believe that??? That is why i had to clone it first!
            clone.and(adj.get(coor[1]));
            // cardinality returns the number of 1 bits! That is the intersection after the and() operation!
            result += clone.cardinality();
        }
        return result;
    }
}