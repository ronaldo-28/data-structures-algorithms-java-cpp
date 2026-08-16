class Solution {
    public int countPairs(List<List<Integer>> coordinates, int k) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap();
        int result = 0;
        for (List<Integer> coordinate : coordinates) {
        	int x = coordinate.get(0);
        	int y = coordinate.get(1);
        	for (int i = 0; i <= k; i++) {
        		int a = x ^ i;
        		if (map.containsKey(a))
        			result += map.get(a).getOrDefault(y ^ (k - i), 0);        			
        	}
        	if (!map.containsKey(x))
        		map.put(x, new HashMap());
        	map.get(x).put(y, map.get(x).getOrDefault(y, 0) + 1);
        }
        return result;
    }
}