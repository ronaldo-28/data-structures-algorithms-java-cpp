class Solution {
    public String[] sortFeatures(String[] features, String[] responses) {
        Map<String, Integer> indexMap = new HashMap<>();
        Integer[] indices = new Integer[features.length];
        for (int i = 0; i < features.length; i += 1) {
            indices[i] = i;
            indexMap.put(features[i], i);
        }

        int[] map = new int[features.length + 1];
        for (String response : responses) {
            String[] segments = response.split(" ");

            boolean[] visited = new boolean[features.length + 1];
            for (String segment : segments) {
                int index = indexMap.getOrDefault(segment, features.length);
                if (!visited[index]) {
                    visited[index] = true;
                    map[index] += 1;
                }
            }
        }

        Arrays.sort(indices, (a, b) -> {
            if (map[a] == map[b]) {
                return a - b;
            }

            return map[b] - map[a];
        });

        String[] segments = new String[features.length];
        for (int i = 0; i < indices.length; i += 1) {
            segments[i] = features[indices[i]];
        }

        return segments;
    }
}