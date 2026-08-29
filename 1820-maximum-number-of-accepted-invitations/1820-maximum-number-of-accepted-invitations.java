class Solution {

    private int[] match;

    private boolean[] visited;

    public int maximumInvitations(int[][] grid) {
        int boys = grid.length;
        int girls = grid[0].length;
        match = new int[girls];
        Arrays.fill(match, -1);
        boolean[] boyMatched = new boolean[boys];
        int matchCount = 0;
        for (int boy = 0; boy < boys; boy++) {
            for (int girl = 0; girl < girls; girl++) {
                if (grid[boy][girl] == 1 && match[girl] == -1) {
                    match[girl] = boy;
                    matchCount++;
                    boyMatched[boy] = true;
                    break;
                }
            }
        }
        for (int boy = 0; boy < boys; boy++) {
            if (!boyMatched[boy]) {
                visited = new boolean[boys];
                if (dfs(boy, grid)) {
                    matchCount++;
                }
            }
        }
        return matchCount;
    }

    private boolean dfs(int boy, int[][] grid) {
        if (visited[boy]) return false;
        visited[boy] = true;
        for (int girl = 0; girl < grid[0].length; girl++) {
            if (grid[boy][girl] == 1) {
                if (match[girl] == -1 || dfs(match[girl], grid)) {
                    match[girl] = boy;
                    return true;
                }
            }
        }
        return false;
    }
}