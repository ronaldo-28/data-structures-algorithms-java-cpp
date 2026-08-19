class Solution {
public:
    string findShortestWay(vector<vector<int>>& maze, vector<int>& ball,
                           vector<int>& hole) {
        int m = maze.size(), n = maze[0].size();
        const int INF = 1e9;

        vector<vector<int>> dist(m, vector<int>(n, INF));
        vector<vector<string>> best(m, vector<string>(n, "~"));

        using T = tuple<int, string, int, int>;
        priority_queue<T, vector<T>, greater<T>> pq;

        dist[ball[0]][ball[1]] = 0;
        best[ball[0]][ball[1]] = "";
        pq.push({0, "", ball[0], ball[1]});

        static const int dr[4] = {1, 0, 0, -1};
        static const int dc[4] = {0, -1, 1, 0};
        static const char dir[4] = {'d', 'l', 'r', 'u'};

        while (!pq.empty()) {
            auto [d, path, r, c] = pq.top();
            pq.pop();

            if (d != dist[r][c] || path != best[r][c])
                continue;

            if (r == hole[0] && c == hole[1])
                return path;

            for (int k = 0; k < 4; ++k) {
                int nr = r, nc = c, steps = 0;

                while (true) {
                    int rr = nr + dr[k];
                    int cc = nc + dc[k];

                    if (rr < 0 || rr >= m || cc < 0 || cc >= n ||
                        maze[rr][cc])
                        break;

                    nr = rr;
                    nc = cc;
                    ++steps;

                    if (nr == hole[0] && nc == hole[1])
                        break;
                }

                if (!steps) continue;

                int nd = d + steps;
                string np = path + dir[k];

                if (nd < dist[nr][nc] ||
                    (nd == dist[nr][nc] && np < best[nr][nc])) {
                    dist[nr][nc] = nd;
                    best[nr][nc] = np;
                    pq.push({nd, np, nr, nc});
                }
            }
        }

        return "impossible";
    }
};