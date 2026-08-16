#include <bits/stdc++.h>
using namespace std;

static const int MAXN     = 400;
static const int SHIFT    = 9;
static const int MASK     = (1 << SHIFT) - 1;
static const int MAXSZ    = (MAXN << SHIFT) + 4;
static const int MAXLATER = 8 * MAXN * MAXN;
static const int STEP     = (1 << SHIFT);

static const unsigned char T_INTERIOR = 0;
static const unsigned char T_TOP      = 1;
static const unsigned char T_BOTTOM   = 2;
static const unsigned char T_LEFT     = 3;
static const unsigned char T_RIGHT    = 4;
static const unsigned char T_TL       = 5;
static const unsigned char T_TR       = 6;
static const unsigned char T_BL       = 7;
static const unsigned char T_BR       = 8;

int           parent_[MAXSZ];
short         rnk_[MAXSZ];
short         distOf_[MAXSZ];
unsigned char cellType[MAXSZ];
int           layerA[MAXN * MAXN];
int           layerB[MAXN * MAXN];
int           laterA[MAXLATER];
int           laterB[MAXLATER];

static inline int find(int x) {
    while (parent_[x] != x) {
        parent_[x] = parent_[parent_[x]];
        x = parent_[x];
    }
    return x;
}
static inline void unite(int a, int b) {
    a = find(a); b = find(b);
    if (a == b) return;
    if (rnk_[a] < rnk_[b]) swap(a, b);
    parent_[b] = a;
    if (rnk_[a] == rnk_[b]) rnk_[a]++;
}

class Solution {
public:
    int maximumSafenessFactor(vector<vector<int>>& grid) {
        int n = grid.size();

        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) return 0;

        int TOP = MAXSZ - 4, BOTTOM = MAXSZ - 3, LEFT = MAXSZ - 2, RIGHT = MAXSZ - 1;

        for (int r = 0; r < n; r++) {
            int base = r << SHIFT;
            int c = 0, id = base;
            for (; c + 4 <= n; c += 4, id += 4) {
                parent_[id]   = id;   rnk_[id]   = 0; distOf_[id]   = -1;
                parent_[id+1] = id+1; rnk_[id+1] = 0; distOf_[id+1] = -1;
                parent_[id+2] = id+2; rnk_[id+2] = 0; distOf_[id+2] = -1;
                parent_[id+3] = id+3; rnk_[id+3] = 0; distOf_[id+3] = -1;
            }
            for (; c < n; c++, id++) {
                parent_[id] = id; rnk_[id] = 0; distOf_[id] = -1;
            }
        }
        parent_[TOP] = TOP; parent_[BOTTOM] = BOTTOM;
        parent_[LEFT] = LEFT; parent_[RIGHT] = RIGHT;
        rnk_[TOP] = rnk_[BOTTOM] = rnk_[LEFT] = rnk_[RIGHT] = 0;

        memset(cellType, T_INTERIOR, (size_t)(n - 1) * STEP + n);
        memset(cellType, T_TOP, n);
        memset(cellType + ((n - 1) << SHIFT), T_BOTTOM, n);
        for (int r = 0; r < n; r++) {
            cellType[r << SHIFT] = T_LEFT;
            cellType[(r << SHIFT) | (n - 1)] = T_RIGHT;
        }
        cellType[0] = T_TL;
        cellType[n - 1] = T_TR;
        cellType[(n - 1) << SHIFT] = T_BL;
        cellType[((n - 1) << SHIFT) | (n - 1)] = T_BR;

        int *curLayer = layerA, *nextLayer = layerB;
        int curCount = 0;
        int thiefCount = 0, thiefR = -1, thiefC = -1;

        for (int r = 0; r < n; r++)
            for (int c = 0; c < n; c++)
                if (grid[r][c] == 1) {
                    int id = (r << SHIFT) | c;
                    distOf_[id] = 0;
                    curLayer[curCount++] = id;
                    thiefCount++;
                    thiefR = r; thiefC = c;
                }

        if (thiefCount == 1) {
            int top = thiefR, bottom = n - 1 - thiefR;
            int left = thiefC, right = n - 1 - thiefC;
            return min({max(top, left), max(top, bottom),
                        max(left, right), max(bottom, right)});
        }

        int d = 0;
        while (curCount > 0) {
            int laterCount = 0;
            int nextCount = 0;

            #define O(nid) \
                if (distOf_[nid] == -1) { \
                    distOf_[nid] = d + 1; \
                    nextLayer[nextCount++] = nid; \
                } else if (distOf_[nid] <= d) { \
                    unite(id, nid); \
                } else { \
                    laterA[laterCount] = id; laterB[laterCount] = nid; laterCount++; \
                }
            #define D(nid) \
                if (distOf_[nid] != -1) { \
                    if (distOf_[nid] <= d) { \
                        unite(id, nid); \
                    } else { \
                        laterA[laterCount] = id; laterB[laterCount] = nid; laterCount++; \
                    } \
                }

            for (int i = 0; i < curCount; i++) {
                int id = curLayer[i];

                switch (cellType[id]) {
                    case T_INTERIOR:
                        O(id - STEP) O(id + STEP) O(id - 1) O(id + 1)
                        D(id - STEP - 1) D(id - STEP + 1) D(id + STEP - 1) D(id + STEP + 1)
                        break;
                    case T_TOP:
                        unite(id, TOP);
                        O(id - 1) O(id + 1) O(id + STEP)
                        D(id + STEP - 1) D(id + STEP + 1)
                        break;
                    case T_BOTTOM:
                        unite(id, BOTTOM);
                        O(id - 1) O(id + 1) O(id - STEP)
                        D(id - STEP - 1) D(id - STEP + 1)
                        break;
                    case T_LEFT:
                        unite(id, LEFT);
                        O(id - STEP) O(id + STEP) O(id + 1)
                        D(id - STEP + 1) D(id + STEP + 1)
                        break;
                    case T_RIGHT:
                        unite(id, RIGHT);
                        O(id - STEP) O(id + STEP) O(id - 1)
                        D(id - STEP - 1) D(id + STEP - 1)
                        break;
                    case T_TL:
                        unite(id, TOP); unite(id, LEFT);
                        O(id + 1) O(id + STEP)
                        D(id + STEP + 1)
                        break;
                    case T_TR:
                        unite(id, TOP); unite(id, RIGHT);
                        O(id - 1) O(id + STEP)
                        D(id + STEP - 1)
                        break;
                    case T_BL:
                        unite(id, BOTTOM); unite(id, LEFT);
                        O(id + 1) O(id - STEP)
                        D(id - STEP + 1)
                        break;
                    case T_BR:
                        unite(id, BOTTOM); unite(id, RIGHT);
                        O(id - 1) O(id - STEP)
                        D(id - STEP - 1)
                        break;
                }
            }
            #undef O
            #undef D

            if (find(TOP)==find(LEFT) || find(TOP)==find(BOTTOM) ||
                find(LEFT)==find(RIGHT) || find(BOTTOM)==find(RIGHT)) return d;

            for (int i = 0; i < laterCount; i++) unite(laterA[i], laterB[i]);
            if (find(TOP)==find(LEFT) || find(TOP)==find(BOTTOM) ||
                find(LEFT)==find(RIGHT) || find(BOTTOM)==find(RIGHT)) return d + 1;

            swap(curLayer, nextLayer);
            curCount = nextCount;
            d++;
        }

        return -1;
    }
};

auto init = []() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    return 'c';
}();