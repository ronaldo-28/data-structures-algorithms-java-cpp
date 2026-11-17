class Solution {
public:
    long long maxArea(vector<vector<int>>& coords) {
        long long ret = -1;
        int coordCount = coords.size();
        sort(coords.begin(), coords.end(), [](const vector<int>& a, const vector<int>& b) { return a[0] < b[0]; });
        if (coords[0][0] == coords[coordCount-1][0])
            return -1;

        int i = 0;
        while (i < coordCount)
        {
            int first = i;
            int x = coords[i][0];
            long long yMin = coords[i][1];
            long long yMax = yMin;
            ++i;
            while (i < coordCount && coords[i][0] == x)
            {
                long long y = coords[i][1];
                yMin = min(yMin, y);
                yMax = max(yMax, y);
                i++;
            }
            if (yMin == yMax)
                continue;
            ret = max(ret, (yMax - yMin) * max(x - coords[0][0], coords[coordCount-1][0] - x));
        }

        sort(coords.begin(), coords.end(), [](const vector<int>& a, const vector<int>& b) { return a[1] < b[1]; });
        if (coords[0][1] == coords[coordCount-1][1])
            return -1;

        i = 0;
        while (i < coordCount)
        {
            int first = i;
            int y = coords[i][1];
            long long xMin = coords[i][0];
            long long xMax = xMin;
            ++i;
            while (i < coordCount && coords[i][1] == y)
            {
                long long x = coords[i][0];
                xMin = min(xMin, x);
                xMax = max(xMax, x);
                i++;
            }
            if (xMin == xMax)
                continue;
            ret = max(ret, (xMax - xMin) * max(y - coords[0][1], coords[coordCount-1][1] - y));
        }

        return ret;

        /*
        int minX[1000001] = {};
        int minY[1000001] = {};
        int maxX[1000001] = {};
        int maxY[1000001] = {};
        int unX[100000];
        int unY[100000];
        int xCount = 0;
        int yCount = 0;

        int firstX = 0x7fffffff;
        int lastX = 0;
        int firstY = 0x7fffffff;
        int lastY = 0;
        for (const vector<int>& coord : coords)
        {
            int x = coord[0];
            int y = coord[1];
            if (minX[y] == 0)
            {
                minX[y] = maxX[y] = x;
                firstY = min(firstY, y);
                lastY = max(lastY, y);
            }
            else if (x != minX[y])
            {
                if (minX[y] == maxX[y])
                    unY[yCount++] = y;
                minX[y] = min(minX[y], x);
                maxX[y] = max(maxX[y], x);
            }

            if (minY[x] == 0)
            {
                minY[x] = maxY[x] = y;
                firstX = min(firstX, x);
                lastX = max(lastX, x);
            }
            else if (y != minY[x])
            {
                if (minY[x] == maxY[x])
                    unX[xCount++] = x;
                minY[x] = min(minY[x], y);
                maxY[x] = max(maxY[x], y);
            }
        }

        if (firstX == lastX || firstY == lastY)
            return -1;

        long long ret = -1;
        for (int i = 0; i < xCount; i++)
        {
            long long x = unX[i];
            long long y = maxY[x] - minY[x];
            ret = max(ret, y * max(x - firstX, lastX - x));
        }
        for (int i = 0; i < yCount; i++)
        {
            long long y = unY[i];
            long long x = maxX[y] - minX[y];
            ret = max(ret, x * max(y - firstY, lastY - y));
        }

        return ret;
        */
    }
};