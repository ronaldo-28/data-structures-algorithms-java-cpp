class Solution {
    private boolean empty(int[] freq) {
        for(int f: freq) if(f > 0) return false;
        return true;
    }
    private String toString(int[] freq) {
        StringBuilder sb = new StringBuilder();
        char c = 'a';
        for(int f: freq) {
            while(f-- > 0) sb.append(c);
            c++;
        }
        return sb.toString();
    }
    public int minStickers(String[] stickers, String target) {
        // Optimization 1: Maintain frequency only for characters present in target
        int[] targetNaiveCount = new int[26];
        for(char c: target.toCharArray()) targetNaiveCount[c - 'a']++;
        int[] index = new int[26];
        int N = 0;  // no of distinct characters in target
        for(int i = 0; i < 26; i++) index[i] = targetNaiveCount[i] > 0 ? N++ : -1;
        int[] targetCount = new int[N];
        int t = 0;
        for(int c: targetNaiveCount) if(c > 0) {
            targetCount[t++] = c;
        }
        int[][] stickersCount = new int[stickers.length][N];
        for(int i = 0; i < stickers.length; i++) {
            for(char c: stickers[i].toCharArray()) {
                int j = index[c - 'a'];
                if(j >= 0) stickersCount[i][j]++;
            }
        }
        // Optimization 2: Remove stickers dominated by some other sticker
        int start = 0;
        for(int i = 0; i < stickers.length; i++) {
            for(int j = start; j < stickers.length; j++) if(j != i) {
                int k = 0;
                while(k < N && stickersCount[i][k] <= stickersCount[j][k]) k++;
                if(k == N) {
                    int[] tmp = stickersCount[i];
                    stickersCount[i] = stickersCount[start];
                    stickersCount[start++] = tmp;
                    break;
                }
            }
        }
        // Perform BFS with target as source and an empty string as destination
        Queue<int[]> Q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Q.add(targetCount);
        int steps = 0;
        while(!Q.isEmpty()) {
            steps++;
            int size = Q.size();
            while(size-- > 0) {
                int[] freq = Q.poll();
                String cur = toString(freq);
                if(visited.add(cur)) {
                    // Optimization 3: Only use stickers that are capable of removing first character from current string
                    int first = cur.charAt(0) - 'a';
                    for(int i = start; i < stickers.length; i++) if(stickersCount[i][first] != 0) {
                        int[] next = freq.clone();
                        for(int j = 0; j < N; j++) next[j] = Math.max(next[j] - stickersCount[i][j], 0);
                        if(empty(next)) return steps;
                        Q.add(next);
                    }
                }
            }
        }
        return -1;
    }
}