#pragma clang optimize on

#pragma GCC target("avx2,bmi2,popcnt,lzcnt")

class FastParser {
    const char* p;
public:
    FastParser(const string& s) : p(s.data()) {}
    
    void skip() {
        while (*p && *p <= ' ') p++;
    }

    char parseType() {
        skip();
        char c = *p;
        while (*p && *p > ' ') p++; 
        return c;
    }

    int parseInt() {
        skip();
        int x = 0;
        while (*p >= '0' && *p <= '9') {
            x = x * 10 + (*p - '0');
            p++;
        }
        return x;
    }

    char parseChar() {
        skip();
        return *p++;
    }
};

class Solution {
private:
    vector<int> head, nextEdge, to;
    int edgeCount = 0;

    vector<int> parent, depth, sz, heavy, headChain, pos, rank;
    
    vector<uint32_t> tree; 
    int n;
    int curPos;

    inline void addEdge(int u, int v) {
        to[edgeCount] = v;
        nextEdge[edgeCount] = head[u];
        head[u] = edgeCount++;
    }

    void dfs_sz(int u, int p, int d) {
        sz[u] = 1;
        parent[u] = p;
        depth[u] = d;
        int maxSz = 0;
        
        for (int e = head[u]; e != -1; e = nextEdge[e]) {
            int v = to[e];
            if (v != p) {
                dfs_sz(v, u, d + 1);
                sz[u] += sz[v];
                if (sz[v] > maxSz) {
                    maxSz = sz[v];
                    heavy[u] = v;
                }
            }
        }
    }

    void dfs_hld(int u, int h) {
        headChain[u] = h;
        pos[u] = curPos++;
        rank[pos[u]] = u; 
        if (heavy[u] != -1) [[likely]] {
            dfs_hld(heavy[u], h);
        }

        for (int e = head[u]; e != -1; e = nextEdge[e]) {
            int v = to[e];
            if (v != parent[u] && v != heavy[u]) {
                dfs_hld(v, v); 
            }
        }
    }

    __attribute__((always_inline)) 
    void updateZKW(int p, uint32_t val) {
        for (tree[p += n] = val; p > 1; p >>= 1) {
            tree[p >> 1] = tree[p] ^ tree[p ^ 1];
        }
    }

    __attribute__((always_inline)) 
    uint32_t queryZKW(int l, int r) {
        uint32_t res = 0;
        for (l += n, r += n; l < r; l >>= 1, r >>= 1) {
            if (l & 1) res ^= tree[l++];
            if (r & 1) res ^= tree[--r];
        }
        return res;
    }

public:
    vector<bool> palindromePath(int n, vector<vector<int>>& edges, string s, vector<string>& queries) {
        this->n = n;
        
        head.assign(n, -1);
        nextEdge.resize(edges.size() * 2);
        to.resize(edges.size() * 2);
        
        parent.resize(n);
        depth.resize(n);
        sz.resize(n);
        heavy.assign(n, -1);
        headChain.resize(n);
        pos.resize(n);
        rank.resize(n);
        tree.resize(2 * n);

        edgeCount = 0;
        for (const auto& e : edges) {
            addEdge(e[0], e[1]);
            addEdge(e[1], e[0]);
        }
        curPos = 0;
        dfs_sz(0, -1, 0);
        dfs_hld(0, 0);

        for (int i = 0; i < n; ++i) 
            tree[n + i] = (1u << (s[rank[i]] - 'a'));
        
        for (int i = n - 1; i > 0; --i) {
            tree[i] = tree[i << 1] ^ tree[i << 1 | 1];
        }

        vector<bool> ans;
        ans.reserve(queries.size());

        for (const auto& qStr : queries) {
            FastParser parser(qStr);
            char type = parser.parseType();

            if (type == 'u') { // update
                int ui = parser.parseInt();
                char c = parser.parseChar();
                s[ui] = c; 
                updateZKW(pos[ui], 1u << (c - 'a'));
            } else { // query
                int u = parser.parseInt();
                int v = parser.parseInt();
                uint32_t mask = 0;

                while (headChain[u] != headChain[v]) {
                    if (depth[headChain[u]] < depth[headChain[v]]) 
                        std::swap(u, v);
                    
                    mask ^= queryZKW(pos[headChain[u]], pos[u] + 1);
                    u = parent[headChain[u]];
                }
                
                if (depth[u] > depth[v]) {
                    std::swap(u, v);
                }
                mask ^= queryZKW(pos[u], pos[v] + 1);
                
                ans.push_back(__builtin_popcount(mask) <= 1);
            }
        }
        return ans;
    }
};

static const auto io_sync_off = []() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(nullptr);
    return nullptr;
}();