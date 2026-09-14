class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        Set<Long> obs = new HashSet<>(obstacles.length * 2);
        for (int[] ob : obstacles) {
            obs.add(pack(ob[0], ob[1]));
        }
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int dir = 0; 

        int x = 0, y = 0;
        int maxDistSq = 0;

        for (int cmd : commands) {
            if (cmd == -2) {          
                dir = (dir + 3) % 4;   
            } else if (cmd == -1) {    
                dir = (dir + 1) % 4;
            } else {
                for (int step = 0; step < cmd; step++) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];
                    if (obs.contains(pack(nx, ny))) {
                        break;
                    }
                    x = nx;
                    y = ny;
                    int d2 = x * x + y * y;
                    if (d2 > maxDistSq) maxDistSq = d2;
                }
            }
        }

        return maxDistSq;
    }
    private long pack(int x, int y) {
        return (((long) x) << 32) ^ (y & 0xffffffffL);  
    }
}