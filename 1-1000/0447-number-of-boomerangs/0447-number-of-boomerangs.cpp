class Solution {
public:
    int dis[501][501];  // Our distances hashmap 
    
    int numberOfBoomerangs(vector<vector<int>>& points) {
        int n = points.size ();
        
        memset(dis, -1, sizeof(dis[0])*points.size()+1);
		// Initialize all the distances in our array map
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int dist = my_distance(points, i, j);   // my_distance to not clash with std::distance. Also, avoids the vector clutter here.
                dis [i][j] = dist;
                dis [j][i] = dist;
            }
        }
        
        int res = 0;
        for (int i = 0; i < n; i++) {
            // take out the array of dists from point i to the rest
            auto &len = dis[i];
            // Sort it so that we can make windows to get our counts
            ranges::sort(len, len+n);
            int last = len[0], count = 1;
            for (int j = 0; j < n; j++)
            {
                // ignore garbage values (dist(i,i) in this case)
                if (len[j] == -1) {
                    res += count * (count - 1);
                    count = 1;
                    continue;
                }
                // if the element is the same as the prev, increase window size
                if (len[j] == last)
                    count++;
                else {  // Else, find the final count and start another window size count
                    res += count * (count - 1);
                    count = 1;
                    last = len[j];
                }
            }
            // take care of the case in which the window ends with the array, so we need to calculate boomarang counts after the loop
            res += count * (count - 1);
        }
        // Return the counts of the boomerangs :)
        return res;
    }
	
    // The inline makes the function feel faster ;)
    inline int my_distance(vector<vector<int>>& points, int i, int j)
    {
        vector<int>& a = points[i];
        vector<int>& b = points[j];
        return (a[0]-b[0])*(a[0]-b[0]) + (a[1]-b[1])*(a[1]-b[1]);   // returning a clean int
    }
};