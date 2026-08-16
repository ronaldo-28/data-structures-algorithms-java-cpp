class Solution {

      public int garbageCollection(String[] garbage, int[] travel) {

        int ans = 0;
        
        // build prefix sum of travelling
        for(int i = 1; i < travel.length; i++) {

            travel[i] += travel[i-1];
        }

        // collect all trash items, each cost 1m
        for(int i = 0; i < garbage.length; i++) {
            // finally we collect all garbage from of each type from all homes
            ans += garbage[i].length();
        }

        int lastIdx = lastIndex(garbage , 'M');
        if(lastIdx > -1) {
            ans += travel[lastIdx - 1];
        }

        lastIdx = lastIndex(garbage , 'P');
        if(lastIdx > -1) {
            ans += travel[lastIdx - 1];
        }

        lastIdx = lastIndex(garbage , 'G');
        if(lastIdx > -1) {
            ans += travel[lastIdx - 1];
        }

        return ans;
    }

    private int lastIndex(String[] garbage, char c) {

        int idx = -1;

        for(int i = garbage.length - 1; i > 0; i--) {

            if(garbage[i].indexOf(c) > -1) {
                return i;
            }
        }
        return idx;
    }
}