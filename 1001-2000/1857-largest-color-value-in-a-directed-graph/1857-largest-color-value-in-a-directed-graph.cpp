#include <vector>
#include <string>
#include <algorithm>

using namespace std;

#pragma GCC optimize("Ofast,unroll-loops")
#pragma GCC target("avx2,bmi,bmi2,lzcnt,popcnt")

class Solution {
    // Статика в BSS — ноль аллокаций в рантайме
    int head[100005], to[100005], nxt[100005], edge_cnt;
    int in_degree[100005];
    int dp[100005][26];
    int q[100005]; // Кастомная очередь на массиве

    inline void add_edge(int u, int v) {
        to[edge_cnt] = v;
        nxt[edge_cnt] = head[u];
        head[u] = edge_cnt++;
    }

public:
    int largestPathValue(string colors, vector<vector<int>>& edges) {
        int n = colors.length();
        int m = edges.size();

        // Быстрая очистка
        edge_cnt = 0;
        for (int i = 0; i < n; ++i) {
            head[i] = -1;
            in_degree[i] = 0;
            for (int j = 0; j < 26; ++j) dp[i][j] = 0;
        }

        for (int i = 0; i < m; ++i) {
            int u = edges[i][0], v = edges[i][1];
            add_edge(u, v);
            in_degree[v]++;
        }

        int front = 0, back = 0;
        for (int i = 0; i < n; ++i) {
            if (in_degree[i] == 0) {
                q[back++] = i;
                dp[i][colors[i] - 'a'] = 1;
            }
        }

        int visited = 0;
        int max_val = 0;

        while (front < back) {
            int u = q[front++];
            visited++;

            // Локальный кеш для текущего цвета и DP строки
            int u_color = colors[u] - 'a';
            int* dp_u = dp[u];
            
            // Сразу проверяем максимум для текущего узла
            for (int c = 0; c < 26; ++c) {
                if (dp_u[c] > max_val) max_val = dp_u[c];
            }

            for (int i = head[u]; i != -1; i = nxt[i]) {
                int v = to[i];
                int* dp_v = dp[v];
                int v_color = colors[v] - 'a';

                // Жесткая оптимизация: обновляем DP соседа
                for (int c = 0; c < 26; ++c) {
                    int val = dp_u[c] + (v_color == c);
                    if (val > dp_v[c]) dp_v[c] = val;
                }

                if (--in_degree[v] == 0) {
                    q[back++] = v;
                }
            }
        }

        return (visited == n) ? max_val : -1;
    }
};

static const int fast_io = []() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    return 0;
}();