#include <vector>
#include <cstring>

using namespace std;

class Solution {
    // Статическое состояние для исключения аллокаций
    unsigned char visited[20][20][20][20]; 
    int q[160000]; // Кольцевой буфер для 0-1 BFS
    int dr[4] = {-1, 1, 0, 0};
    int dc[4] = {0, 0, -1, 1};

public:
    int minPushBox(vector<vector<char>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int si, sj, bi, bj, ti, tj;

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 'S') { si = i; sj = j; }
                else if (grid[i][j] == 'B') { bi = i; bj = j; }
                else if (grid[i][j] == 'T') { ti = i; tj = j; }
            }
        }

        memset(visited, 0x3f, sizeof(visited)); // Заполняем "бесконечностью"
        
        int head = 80000, tail = 80000;
        // Упаковка: (pushes << 24) | (br << 18) | (bc << 12) | (pr << 6) | pc
        q[tail++] = (0 << 24) | (bi << 18) | (bj << 12) | (si << 6) | sj;
        visited[bi][bj][si][sj] = 0;

        while (head != tail) {
            int curr = q[head++];
            int d = curr >> 24;
            int br = (curr >> 18) & 0x3F;
            int bc = (curr >> 12) & 0x3F;
            int pr = (curr >> 6) & 0x3F;
            int pc = curr & 0x3F;

            if (br == ti && bc == tj) return d;

            for (int i = 0; i < 4; ++i) {
                int npr = pr + dr[i], npc = pc + dc[i];
                if (npr < 0 || npr >= m || npc < 0 || npc >= n || grid[npr][npc] == '#') continue;

                if (npr == br && npc == bc) {
                    // Толчок (Вес 1)
                    int nbi = br + dr[i], nbj = bc + dc[i];
                    if (nbi >= 0 && nbi < m && nbj >= 0 && nbj < n && grid[nbi][nbj] != '#') {
                        if (visited[nbi][nbj][npr][npc] > d + 1) {
                            visited[nbi][nbj][npr][npc] = d + 1;
                            q[tail++] = ((d + 1) << 24) | (nbi << 18) | (nbj << 12) | (npr << 6) | npc;
                        }
                    }
                } else {
                    // Ход игрока (Вес 0)
                    if (visited[br][bc][npr][npc] > d) {
                        visited[br][bc][npr][npc] = d;
                        q[--head] = (d << 24) | (br << 18) | (bc << 12) | (npr << 6) | npc;
                    }
                }
            }
        }
        return -1;
    }
};