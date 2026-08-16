class Solution {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        int userNum = languages.length;
        boolean[] teachingCandidates = new boolean[userNum];
        boolean[][] spokenLang = new boolean[userNum][n+1];

        // O(U * L)
        for (int i = 0; i < userNum; i++) {
            int[] langs = languages[i];
            for (int lang : langs) {
                spokenLang[i][lang] = true;
            }
        }

        // O(F * N). F is number of friendships. N is the number of languages
        for (int[] friendship : friendships) {
            int u = friendship[0] - 1;
            int v = friendship[1] - 1;
            boolean canComm = false;
            for (int i = 1; i <= n; i++) {
                if (spokenLang[u][i] && spokenLang[v][i]) {
                    canComm = true;
                    break;
                }
            }
            if (!canComm) {
                teachingCandidates[u] = true;
                teachingCandidates[v] = true;
            }
        }
        int totalCandidates = 0;
        int[] langFreq = new int[n+1]; // langFreq[i] is the number of candidates who already know i-th language
        // O(U * N)
        for (int i = 0; i < userNum; i++) {
            if (teachingCandidates[i]) {
                totalCandidates++;
                for (int j = 1; j <= n; j++) {
                    if (spokenLang[i][j]) {
                        langFreq[j]++;
                    }
                }
            }
        }
        if (totalCandidates == 0) return 0;
        // O(N)
        int maxFreq = 0;
        for (int i = 1; i <= n; i++) {
            maxFreq = Math.max(maxFreq, langFreq[i]);
        }
        return totalCandidates - maxFreq;
    }
}
/*
n = 2, languages = [[1],[2],[1,2]], friendships = [[1,2],[1,3],[2,3]]

1: {1,3}
2: {2,3}

n = 3, languages = [[2],[1,3],[1,2],[3]], friendships = [[1,4],[1,2],[3,4],[2,3]]

user 1: lan 2, *lan 3
user 2: lan 1 and lan 3
user 3: lan 1 and lan 2, *lan 3
user 4: lan 3

lanMap
user -> lan
1: {2}
2: {1, 3}
3: {1, 2}
4: {3}

lan -> user
1 -> {2,3}
2 -> {1,3}
3 -> {2,4}

friendship adjacency list
1: [4, 2]
2: [1,3]
3: [4,2]
4: [1,3]

language 2:
{1, 3, 4, 2}
[1,4] => add 4. + 1
[1,2] => add 2. + 1
[3,4] => 
[2,3] =>  
*/