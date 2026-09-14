class Solution {
    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
        Map<String, String> parent = new HashMap<>(regions.size() << 4);
        for(List<String> region : regions) {
            int n = region.size();
            String s = region.get(0);
            for(int i = 1; i < n; i++) parent.put(region.get(i), s);
        }
        String s1 = region1, s2 = region2;
        while(!s1.equals(s2)) {
            s1 = parent.getOrDefault(s1, region2);
            s2 = parent.getOrDefault(s2, region1);
        }
        return s1;
    }
}