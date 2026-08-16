class Solution {

    List<Integer>[] tree, subTree;
    int subTreeRoot, maxPath;
    boolean[] inStack; // do not allow to travel upwards in the rooted tree
    int[] ans;

    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        buildTree(n, edges);
        ans = new int[n - 1];
        solve(0);
        return ans;
    }

    private void buildTree(int n, int[][] edges) {
        tree = new List[n];
        subTree = new List[n];
        inStack = new boolean[n];
        for (int i = 0; i < n; ++i) {
            tree[i] = new ArrayList<>();
            subTree[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            tree[e[0] - 1].add(e[1] - 1);
            tree[e[1] - 1].add(e[0] - 1);
        }
    }

    private void solve(int i) {
        subTreeRoot = i;
        inStack[i] = true;
        buildSubTree(0, 0, Collections.singletonList(i), new ArrayList<>());
        for (int e : tree[i]) {
            if (inStack[e]) {
                continue;
            }
            solve(e);
        }
    }

    private void buildSubTree(int i, int j, List<Integer> fronteer, List<Integer> next) {
        if (i >= fronteer.size()) {
            buildNextLevel(next);
            return;
        }
        int curr = fronteer.get(i);
        if (j >= tree[curr].size()) {
            buildSubTree(i + 1, 0, fronteer, next);
        } else {
            int succ = tree[curr].get(j);
            if (inStack[succ]) {
                buildSubTree(i, j + 1, fronteer, next);
                return;
            }
            inStack[succ] = true;
            next.add(succ);
            subTree[curr].add(succ);
            buildSubTree(i, j + 1, fronteer, next);
            next.remove(next.size() - 1);
            subTree[curr].remove(subTree[curr].size() - 1);
            buildSubTree(i, j + 1, fronteer, next);
            inStack[succ] = false;
        }
    }

    private void buildNextLevel(List<Integer> next) {
        if (next.isEmpty()) { // subTree is done, add it to the answer
            maxPath = 0;
            computeMaxPath(subTreeRoot);
            if (maxPath > 1) {
                ++ans[maxPath - 2];
            }
        } else {
            buildSubTree(0, 0, next, new ArrayList<>());
        }
    }

    private int computeMaxPath(int i) {
        int max = 0, prevMax = 0;
        for (int child : subTree[i]) {
            int h = computeMaxPath(child);
            if (h >= max) {
                prevMax = max;
                max = h;
            } else {
                prevMax = Integer.max(prevMax, h);
            }
        }
        maxPath = Integer.max(maxPath, prevMax + max + 1);
        return max + 1;
    }
}