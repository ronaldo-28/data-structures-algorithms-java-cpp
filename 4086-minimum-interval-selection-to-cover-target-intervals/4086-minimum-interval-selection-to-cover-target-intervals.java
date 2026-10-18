class Solution {
    public int minSelection(int[][] availableIntervals, int[][] targetIntervals) {

        int mc = 200005;

        int dif [] = new int[mc];

        int mt = 0;

        for(int [] ivl : targetIntervals){

            int l = ivl[0] * 2;

            int r = ivl[1] * 2;

            dif[l]++;

            dif[r + 1] --;

            if(r > mt){
                mt = r;
            }
        }

        int[] bst = new int[mc];

        for(int[] ivl : availableIntervals){

            int l = ivl[0] * 2;
            int r = ivl[1] * 2;

            if(r > bst[l]){
                bst[l] = r;
            }
        }

        for(int i = 1; i <= mt; i++){

            if(bst[i - 1] > bst[i]){

                bst[i] = bst[i - 1];
            }
        }

        int ans = 0;

        int cuo = 0;

        int act = 0;

        for(int i = 1; i <= mt; i++){

            act += dif[i];

            if(act > 0 && i > cuo){

                if(bst[i] < i){

                    return -1;
                }

                ans++;
                cuo = bst[i];
            }
        }

        return ans;
    }
}