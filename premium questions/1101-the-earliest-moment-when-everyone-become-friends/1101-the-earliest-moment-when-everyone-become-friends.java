class Solution {
    public class DSU {
        private int[] parent;
        private int[] size;
        public int groups;
        public DSU(int n) {
            parent = new int[n];
            size = new int[n];
            groups = n;
            for (int i = 0; i < n;i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        public int find(int p) {
            if (p != parent[p]) {
                parent[p] = find(parent[p]);
            }
            return parent[p];
        }
        public boolean union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return false;
            if(size[rootP] < size[rootQ]) {
                size[rootQ] += size[rootP];
                parent[rootP] = rootQ;
            } else {
                size[rootP] =+ size[rootQ];
                parent[rootQ] = rootP;
            }
            groups--;
            return true;
        }
    }
    public int earliestAcq(int[][] logs, int n) {
        // Arrays.sort(logs, (a, b) -> a[0] - b[0]);
        qsort(logs, 0, logs.length - 1);
        var dsu = new DSU(n);
        for (int[] log : logs) {
            dsu.union(log[1], log[2]);
            if (dsu.groups == 1) {
                return log[0];
            }
        }
        return -1;
    }

    private void qsort(int[][] arr, int left, int right) {
        if (left >= right) return;
        int pivotIdx = partition(arr, left, right);
        qsort(arr, left, pivotIdx - 1);
        qsort(arr, pivotIdx + 1, right);
    }

    private int partition(int[][] arr, int left, int right) {
        int pivot = arr[right][0];
        int i = left;
        for (int j = left; j < right; j++) {
            if (arr[j][0] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, right);
        return i;
    }

    private void swap(int[][] arr, int x, int y) {
        int[] temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}


/**

logs = [[20190101,0,1],
[20190104,3,4],
[20190107,2,3],
[20190211,1,5],
[20190224,2,4],
[20190301,0,3],
[20190312,1,2],
[20190322,4,5]], n = 6



 */