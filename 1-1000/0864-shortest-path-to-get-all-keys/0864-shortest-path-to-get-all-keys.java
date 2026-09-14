class Solution {
public int shortestPathAllKeys(String[] grid) {
        int directions[][] = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int X = grid.length;
        int Y = grid[0].length();
        int startX = -1, startY = -1;
        int numberOfKeys = 0;
        int currentChar;
        int[][] g = new int[X + 2][Y + 2];


        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                currentChar = grid[i].charAt(j);
                g[i + 1][j + 1] = currentChar;
                if (currentChar == '@') {
                    startX = i + 1;
                    startY = j + 1;
                    continue;
                }
                if (Character.isLowerCase(currentChar))
                    numberOfKeys++;
            }
        }

        for (int i = 0; i < X + 2; i++) {
            g[i][0] = '#';
            g[i][Y + 1] = '#';
        }
        for (int j = 0; j < Y + 2; j++) {
            g[0][j] = '#';
            g[X + 1][j] = '#';
        }
        int desiredKeysMask = (int) Math.pow(2, numberOfKeys) - 1;
        boolean[][][] visited = new boolean[X + 1][Y + 1][(int) Math.pow(2, numberOfKeys)];
        visited[startX][startY][0] = true;

        int[] queue = new int[X * Y * (desiredKeysMask + 1)];
        int queueStart = 0;
        int queueEnd = 0;
        queue[queueStart++] = (startX << 6) + startY;

        int x, y, keys;
        int nextX, nextY;
        int step = 0;


        int cells_with_this_step;
        while ((cells_with_this_step = queueStart - queueEnd) > 0) {

            while (cells_with_this_step-- > 0) {
                int status = queue[queueEnd++];
                x = (status >> 6) & 0x3F;
                y = status & 0x3F;
                keys = status >> 12;

                currentChar = g[x][y];

                if ('a' <= currentChar && currentChar <= 'f')
                    keys |= 1 << (currentChar - 'a');

                if (keys == desiredKeysMask)
                    return step;

                for (int[] d : directions) {
                    nextX = x + d[0];
                    nextY = y + d[1];
                    currentChar = g[nextX][nextY];
                    if (currentChar == '#')
                        continue;
                    if ('A' <= currentChar && currentChar <= 'F' &&
                            (keys & 1 << (currentChar - 'A')) == 0)
                        continue;
                    if (visited[nextX][nextY][keys])
                        continue;
                    queue[queueStart++] = (keys << 12) + (nextX << 6) + nextY;
                    visited[nextX][nextY][keys] = true;
                }
            }
            step++;
        }
        return -1;
    }
}