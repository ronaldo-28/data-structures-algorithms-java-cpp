class Solution {

    public static int smallestCommonElement(int[][] mat) {
        return smallestCommonElementUsingBinarySearch(mat);
    }



    /**
    
        [1, 2, 3, 4, 5 ]
        [2, 4, 5, 8, 10]
        [3, 5, 7, 9, 11]
        [1, 3, 5, 7, 9 ]
    
    
     * @TimeComplexity O(mn)
     * @SpaceComplexity O(n)
     */
    public static int smallestCommonElementUsingHashSet(int[][] mat) {
        Set<Integer> set = new HashSet<>();
        for (int x : mat[0]) {
            set.add(x);
        }

        for(int r=1; r<mat.length; r++) {
            Set<Integer> commonSet = new HashSet<>();
            for (int x: mat[r]) {
                if(set.contains(x)) commonSet.add(x);
            }
            set = commonSet;
        }

        if (set.isEmpty()) return -1;

        int min = Integer.MAX_VALUE;
        for (int x: set) {
            min = Math.min(min, x);
        }

        return min;
    }



    /**
     * @TimeComplexity O(mn)
     * @SpaceComplexity O(n)
     */
    public static int smallestCommonElementUsingHashMap(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] ints : mat) {
            for (int x: ints) {
                map.merge(x, 1, Integer::sum);
            }
        }
        int min = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == rows) {
                min = Math.min(min, entry.getKey());
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }




    /**
     * @TimeComplexity O(mn)
     * @SpaceComplexity O(1)
     */
    public static int smallestCommonElementUsingIntArray(int[][] mat) {
        int count[] = new int[10001];
        int n = mat.length, m = mat[0].length;
        for (int[] row: mat) {
            for (int x : row) {
                if (++count[x] == n) {
                    return x;
                }
            }
        }
        return -1;
    }





    /**
     * @TimeComplexity O(mnlogn)
     * @SpaceComplexity O(1)
     */
    public static int smallestCommonElementUsingBinarySearch(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        for (int x:  mat[0]) { // first row
            boolean found = true;
            for (int i = 1; i < n && found; ++i) { // rest of the rows other than first
                found = Arrays.binarySearch(mat[i], x) >= 0; // if false we don't need to check the next rows
            }
            if (found) {
                return x;
            }
        }
        return -1;
    }





    /**
     * @TimeComplexity O(mn)
     * @SpaceComplexity O(n)
     */
    public static int smallestCommonElementUsingRowPositions1(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        int pos[] = new int[n], cur_max = 0, cnt = 0;
        while (true) {
            for (int i = 0; i < n; ++i) {
                while (pos[i] < m && mat[i][pos[i]] < cur_max) {
                    ++pos[i];
                }
                if (pos[i] >= m) {
                    return -1;
                }
                if (mat[i][pos[i]] != cur_max) {
                    cnt = 1;
                    cur_max = mat[i][pos[i]];
                } else if (++cnt == n) {
                    return cur_max;
                }
            }
        }
    }





    /**
     * @TimeComplexity O(mn)
     * @SpaceComplexity O(n)
     */    public static int smallestCommonElementUsingRowPositions2(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        int pos[] = new int[n], cur_max = 0, cnt = 0;
        while (true) {
            for (int i = 0; i < n; ++i) {
                pos[i] = metaSearch(mat[i], pos[i], cur_max);
                if (pos[i] >= m) {
                    return -1;
                }
                if (mat[i][pos[i]] != cur_max) {
                    cnt = 1;
                    cur_max = mat[i][pos[i]];
                } else if (++cnt == n) {
                    return cur_max;
                }
            }
        }
    }

    private static int metaSearch(int[] row, int pos, int val) {
        int sz = row.length, d = 1;
        while (pos < sz && row[pos] < val) {
            d <<= 1;
            if (row[Math.min(pos + d, sz - 1)] >= val) {
                d = 1;
            }
            pos += d;
        }
        return pos;
    }    
}