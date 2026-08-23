import java.util.*;

class Solution {
    public List<String> mostVisitedPattern(String[] usernames, int[] timestamps, String[] websites) {

        // sort by timestamp
        int n = usernames.length;
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) {
            order[i] = i;
        }

        Arrays.sort(order, (a, b) -> Integer.compare(timestamps[a], timestamps[b]));

        // map users to their visited websites in order
        Map<String, List<String>> userToWebsitesVisited = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int index = order[i];

            String user = usernames[index];
            String website = websites[index];
            
            userToWebsitesVisited.putIfAbsent(user, new ArrayList<>());
            userToWebsitesVisited.get(user).add(website);
        }

        List<String> bestPattern = null;
        int maxFreq = 0;

        // calculate all the patterns; the websites should be in order
        Map<List<String>, Integer> patternToFreq = new HashMap<>();
        
        for (String user : userToWebsitesVisited.keySet()) {
            if (userToWebsitesVisited.get(user).size() >= 3) {
                Set<List<String>> patterns = new HashSet<>();
                backtrack(userToWebsitesVisited.get(user), 0, new ArrayList<>(), patterns);
                
                for (List<String> pattern : patterns) {
                    int newFreq = patternToFreq.getOrDefault(pattern, 0) + 1;
                    patternToFreq.put(pattern, newFreq);
                    
                    if (newFreq > maxFreq) {
                        maxFreq = newFreq;
                        bestPattern = pattern;
                    } else if (newFreq == maxFreq) {
                        if (bestPattern == null || compareLists(pattern, bestPattern) < 0) {
                            bestPattern = pattern;
                        }
                    }
                }
            }
        }

        return bestPattern;
    }

    private void backtrack(List<String> websites, int i, List<String> current, Set<List<String>> patterns) {
        if (current.size() == 3) {
            patterns.add(new ArrayList<>(current));
            return;
        }

        int n = websites.size();
        for (int j = i; j < n; j++) {
            current.add(websites.get(j));
            backtrack(websites, j + 1, current, patterns);
            current.remove(current.size() - 1);
        }
    }

    private int compareLists(List<String> a, List<String> b) {
        for (int i = 0; i < 3; i++) {
            int cmp = a.get(i).compareTo(b.get(i));
            if (cmp != 0) return cmp;
        }
        return 0;
    }
}