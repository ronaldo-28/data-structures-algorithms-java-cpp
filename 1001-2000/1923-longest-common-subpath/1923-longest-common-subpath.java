import java.util.HashMap;
import java.util.Map;

class State {
    final int len;
    State link;
    Map<Integer, State> next;
    int ans, max;

    State(int len, State link) {
        this.len = len;
        this.link = link;
        this.next = new HashMap<>();
        this.ans = len;
    }

    State(int len, State link, Map<Integer, State> next) {
        this.len = len;
        this.link = link;
        this.next = new HashMap<>(next);
        this.ans = len;
    }
}

class Sam {
    private final State[] container;
    private int top = 0;

    private State newState(int len, State link) {
        State s = new State(len, link);
        container[top++] = s;
        return s;
    }

    private State newState(int len, State link, Map<Integer, State> next) {
        State s = new State(len, link, next);
        container[top++] = s;
        return s;
    }

    public Sam(int[] path) {
        container = new State[path.length * 2];
        State root = newState(0, null), last = root;
        for (int x : path) {
            State cur = newState(last.len + 1, root);
            for (State p = last; p != null; p = p.link) {
                State q = p.next.putIfAbsent(x, cur);
                if (q == null) continue;
                if (q.len == p.len + 1) {
                    cur.link = q;
                } else {
                    State clone = newState(p.len + 1, q.link, q.next);
                    while (p.next.replace(x, q, clone)) if ((p = p.link) == null) break;
                    cur.link = q.link = clone;
                }
                break;
            }
            last = cur;
        }
    }

    void visit(int[] path) {
        for (int i = 0; i < top; ++i) container[i].max = 0;
        State p = container[0];
        int len = 0;
        for (int x : path) {
            while (true) {
                State q = p.next.get(x);
                if (q != null) {
                    p = q;
                    p.max = Math.max(p.max, ++len);
                    for (State t = p.link; t.max < t.len; t = t.link) t.max = t.len;
                    break;
                }
                if (p.link == null) break;
                p = p.link;
                len = p.len;
            }
        }
        for (int i = 0; i < top; ++i) {
            State state = container[i];
            state.ans = Math.min(state.ans, state.max);
        }
    }

    int longestCommonSubpath() {
        int ans = 0;
        for (int i = 0; i < top; ++i) ans = Math.max(ans, container[i].ans);
        return ans;
    }
}

public class Solution {
    public static int longestCommonSubpath(int n, int[][] paths) {
        if (paths.length == 0) return 0;
        int[] shortest = paths[0];
        for (int[] path : paths) if (path.length < shortest.length) shortest = path;
        Sam sam = new Sam(shortest);
        for (int[] path : paths) if (path != shortest) sam.visit(path);
        return sam.longestCommonSubpath();
    }
}