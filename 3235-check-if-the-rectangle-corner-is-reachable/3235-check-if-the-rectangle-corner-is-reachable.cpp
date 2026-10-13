#pragma GCC optimize("Ofast,unroll-loops,fast-math")
#pragma GCC target("avx,avx2,fma,sse4,popcnt,lzcnt,bmi,bmi2")

static constexpr size_t mxal = alignof(max_align_t);
alignas(mxal) static unsigned char buf[256 * 1024 * 1024];
static size_t pos = 0;

void* operator new(size_t sz) {
    size_t pad = (mxal - (pos % mxal)) % mxal;
    pos += pad + sz;
    return (void*)(&buf[pos - sz]);
}

void* operator new[](size_t sz) { return operator new(sz); }

void operator delete(void*) noexcept {}
void operator delete[](void*) noexcept {}
void operator delete(void*, size_t) noexcept {}
void operator delete[](void*, size_t) noexcept {}

auto speedUp = []() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    return 0;
}();

class Solution {
    enum Side {
        NONE = 0,
        BOTTOM = 1 << 0,
        TOP = 1 << 1,
        LEFT = 1 << 2,
        RIGHT = 1 << 3
    };

    bool contains(long long px, long long py, long long ox, long long oy, long long r) {
        const long long dx = px - ox;
        const long long dy = py - oy;
        return dx * dx + dy * dy <= r * r;
    }

    int intersectingSides(int xCorner, int yCorner, int ox, int oy, int r) {
        const long long X = xCorner;
        const long long Y = yCorner;
        const long long OX = ox;
        const long long OY = oy;
        const long long R = r;
        const long long R2 = R * R;

        auto intersects = [&](long long px, long long py) {
            const long long dx = px - OX;
            const long long dy = py - OY;
            return dx * dx + dy * dy <= R2;
        };

        int result = NONE;

        // Bottom: y = 0
        {
            const long long px = std::clamp(OX, 0LL, X);
            if (intersects(px, 0))
                result |= BOTTOM;
        }

        // Top: y = Y
        {
            const long long px = std::clamp(OX, 0LL, X);
            if (intersects(px, Y))
                result |= TOP;
        }

        // Left: x = 0
        {
            const long long py = std::clamp(OY, 0LL, Y);
            if (intersects(0, py))
                result |= LEFT;
        }

        // Right: x = X
        {
            const long long py = std::clamp(OY, 0LL, Y);
            if (intersects(X, py))
                result |= RIGHT;
        }

        return result;
    }

    // True if the two circles have a common point strictly inside
    // the rectangle.
    bool intersectsInside(int xCorner, int yCorner, const vector<int>& a,
                          const vector<int>& b) {
        const long long x1 = a[0];
        const long long y1 = a[1];
        const long long r1 = a[2];

        const long long x2 = b[0];
        const long long y2 = b[1];
        const long long r2 = b[2];

        const long long dx = x1 - x2;
        const long long dy = y1 - y2;
        const long long sumR = r1 + r2;

        // The disks don't intersect at all.
        if (dx * dx + dy * dy > sumR * sumR)
            return false;

        /*
         * A guaranteed point in the intersection is

         *   P = (r2 * C1 + r1 * C2) / (r1 + r2)
         *
         * Check whether P is strictly inside the rectangle,
         * avoiding floating point.
         */
        const long long px = x1 * r2 + x2 * r1;
        const long long py = y1 * r2 + y2 * r1;

        return px < sumR * xCorner && py < sumR * yCorner;
    }

    bool reachesBlockingSide(int i, int xCorner, int yCorner,
                             const vector<vector<int>>& circles,
                             vector<bool>& visited) {
        visited[i] = true;

        const auto& c = circles[i];

        const int mask = intersectingSides(xCorner, yCorner, c[0], c[1], c[2]);

        // This component has reached the right or bottom boundary.
        if (mask & (RIGHT | BOTTOM))
            return true;

        // Explore all circles connected to this one inside the rectangle.
        for (int j = 0; j < (int)circles.size(); ++j) {
            if (visited[j])
                continue;

            if (!intersectsInside(xCorner, yCorner, c, circles[j])) {
                continue;
            }

            if (reachesBlockingSide(j, xCorner, yCorner, circles, visited)) {
                return true;
            }
        }

        return false;
    }

public:
    bool canReachCorner(int xCorner, int yCorner,
                        vector<vector<int>>& circles) {
        const int n = circles.size();

        /*
         * First, neither endpoint can be touched by an obstacle.
         */
        for (const auto& c : circles) {
            if (contains(0, 0, c[0], c[1], c[2]) ||
                contains(xCorner, yCorner, c[0], c[1], c[2])) {
                return false;
            }
        }

        vector<bool> visited(n);

        /*
         * A blocking component must start from either
         * the left or top boundary.
         *
         * If that component reaches the right or bottom
         * boundary, it separates the two corners.
         */
        for (int i = 0; i < n; ++i) {
            if (visited[i])
                continue;

            const auto& c = circles[i];

            const int mask =
                intersectingSides(xCorner, yCorner, c[0], c[1], c[2]);

            if (!(mask & (LEFT | TOP)))
                continue;

            if (reachesBlockingSide(i, xCorner, yCorner, circles, visited)) {
                return false;
            }
        }

        return true;
    }
};