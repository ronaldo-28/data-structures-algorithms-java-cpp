class Solution {
    public String kthLuckyNumber(int k) {

        if(k == 1) {
            return "4";
        }

        // find simulated tree height then we can find the kth item in the tree
        // as the tree complecte and full binary tree with 4 7 two branches
        int h = 0;
        int p = 0;
        while(p < k) {
            h++;
            p = p + (1 << h);
        }

        // System.out.println("Height: " + h);

        char ans[] = new char[h];
        int i = 0;
        int half = (1 << h) - 1; // total nodes half
        int start = half, end = 2 * half; // last level leaves label
        while(start < end) {
            // need to check which branch of simulated binary tree, k exists in the range
            int middle = (start + end) / 2;
            if(k <= middle) {
                // left side
                ans[i++] = '4';
                end = middle;
            } else {
                // right side
                ans[i++] = '7';
                start = middle + 1;
            }
        }

        return new String(ans);
    }
}