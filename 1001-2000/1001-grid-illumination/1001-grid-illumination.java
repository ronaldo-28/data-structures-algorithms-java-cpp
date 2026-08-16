class Solution {
    private int n;
    private HashMap<Integer, Integer> rowIllum;
    private HashMap<Integer, Integer> colIllum;
    private HashMap<Integer, Integer> dia1Illum;
    private HashMap<Integer, Integer> dia2Illum;
    private HashMap<Integer, ArrayList<Integer>> lamps;

    public boolean isIlluminated(int x, int y) {
        return rowIllum.getOrDefault(x, 0) > 0 || colIllum.getOrDefault(y, 0) > 0 || 
                dia1Illum.getOrDefault(x - y, 0) > 0 || dia2Illum.getOrDefault(x + y, 0) > 0;
    }
    public void turnOff(int x, int y) {
        for(int i = Math.max(x - 1, 0); i < Math.min(x + 2, n); i++) {
            if(lamps.containsKey(i)) {
                ArrayList<Integer> l = lamps.get(i);
                for(int j = Math.max(y - 1, 0); j < Math.min(y + 2, n); j++) {
                    if(l.contains(j)) {
                        rowIllum.put(i, rowIllum.get(i) - 1);
                        colIllum.put(j, colIllum.get(j) - 1);
                        dia1Illum.put(i - j, dia1Illum.get(i - j) - 1);
                        dia2Illum.put(i + j, dia2Illum.get(i + j) - 1);
                        l.remove((Integer)j);
                    }
                    if(l.isEmpty()) {
                        lamps.remove(i);
                    }
                }
            }
        }
    }
    public void turnOn(int x, int y) {
        if(!lamps.containsKey(x) || !lamps.get(x).contains(y)) {
            lamps.computeIfAbsent(x, k -> {
                return new ArrayList<Integer>();
            }).add(y);
            rowIllum.put(x, rowIllum.getOrDefault(x, 0) + 1);
            colIllum.put(y, colIllum.getOrDefault(y, 0) + 1);
            dia1Illum.put(x - y, dia1Illum.getOrDefault(x - y, 0) + 1);
            dia2Illum.put(x + y, dia2Illum.getOrDefault(x + y, 0) + 1);
        }
    }
    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        this.n = n;
        rowIllum = new HashMap<Integer, Integer>();
        colIllum = new HashMap<Integer, Integer>();
        dia1Illum = new HashMap<Integer, Integer>();
        dia2Illum = new HashMap<Integer, Integer>();
        this.lamps = new HashMap<Integer, ArrayList<Integer>>();

        for(int[] l : lamps) {
            turnOn(l[0], l[1]);
        }
        int[] ans = new int[queries.length];
        int index = 0;
        for(int[] q : queries) {
            if(isIlluminated(q[0], q[1])) {
                ans[index] = 1;
                turnOff(q[0], q[1]);
            }
            index++;
        }
        return ans;
    }
}