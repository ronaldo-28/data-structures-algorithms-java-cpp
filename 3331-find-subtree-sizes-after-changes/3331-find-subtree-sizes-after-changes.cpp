class Solution {
    vector<vector<int>> children;
    vector<int> newParent, sz;
    int last[26];
    string str;

    void dfs(int u) {
        int c = str[u] - 'a';
        int prev = last[c];
        if (u != 0 && prev != -1) newParent[u] = prev;   // najbliższy przodek z tą samą literą

        last[c] = u;
        for (int v : children[u]) dfs(v);
        last[c] = prev;                                  // przywracamy przy wyjściu

        if (u != 0) sz[newParent[u]] += sz[u];           // dorzucamy rozmiar do NOWEGO rodzica
    }

public:
    vector<int> findSubtreeSizes(vector<int>& parent, string s) {
        int n = parent.size();
        str = s;
        children.assign(n, {});
        for (int i = 1; i < n; ++i) children[parent[i]].push_back(i);

        newParent = parent;
        sz.assign(n, 1);
        fill(begin(last), end(last), -1);

        dfs(0);
        return sz;
    }
};