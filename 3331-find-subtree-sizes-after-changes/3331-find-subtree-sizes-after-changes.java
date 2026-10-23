class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
              int n = parent.length;
        int[] head = new int[n], next = new int[n];
        Arrays.fill(head, -1);
        for (int i = 1; i < n; i++) { next[i] = head[parent[i]]; head[parent[i]] = i; }
        int[] last = new int[26], saved = new int[n], np = new int[n];
        int[] order = new int[n], stack = new int[n], it = head.clone();
        Arrays.fill(last, -1);
        int sp = 0, cnt = 0, c0 = s.charAt(0) - 'a';
        saved[0] = last[c0]; last[c0] = 0; np[0] = -1;
        order[cnt++] = 0; stack[sp++] = 0;
        while (sp > 0) {
            int u = stack[sp - 1];
            if (it[u] == -1) {
                last[s.charAt(u) - 'a'] = saved[u];
                sp--;
                continue;
            }
            int v = it[u];
            it[u] = next[v];
            int c = s.charAt(v) - 'a';
            np[v] = last[c] != -1 ? last[c] : u;
            saved[v] = last[c];
            last[c] = v;
            order[cnt++] = v;
            stack[sp++] = v;
        }
        int[] size = new int[n];
        Arrays.fill(size, 1);
        for (int i = n - 1; i > 0; i--) size[np[order[i]]] += size[order[i]];
        return size;  
    }
}