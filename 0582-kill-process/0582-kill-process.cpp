class Solution {
public:
    vector<int> killProcess(vector<int>& pid, vector<int>& ppid, int kill) {
        static int head[50001], nxt[50000], child[50000];
        fill_n(head, 50001, -1);

        const int n = pid.size();
        for (int i = 0; i < n; ++i) {
            child[i] = pid[i];
            nxt[i] = head[ppid[i]];
            head[ppid[i]] = i;
        }

        vector<int> ans;
        ans.reserve(n);

        int st[50000], top = 0;
        st[top++] = kill;

        while (top) {
            int u = st[--top];
            ans.push_back(u);

            for (int e = head[u]; e != -1; e = nxt[e])
                st[top++] = child[e];
        }

        return ans;
    }
};