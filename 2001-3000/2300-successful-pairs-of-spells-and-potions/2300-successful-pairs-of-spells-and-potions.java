// class Solution {
//     public int[] successfulPairs(int[] spells, int[] potions, long success) {
//         Arrays.sort(potions);
//         int[] result = new int[spells.length];
//         int i = 0;
//         int len = potions.length;
//         for(int spell : spells){
//             int idx = binary(potions, spell, success);
//             result[i++] = len-idx;
//         }
//         return result;
//     }
//     private int binary(int[] potions, int spell, long success) {
//         int start = 0, end = potions.length - 1;
//         while (start <= end) {
//             int mid = start + (end - start) / 2;
//             long product = (long) spell * potions[mid]; // cast to long to avoid overflow
//             if (product >= success) {
//                 end = mid - 1; // go left to find first valid
//             } else {
//                 start = mid + 1; // go right
//             }
//         }
//         return start; // first index where spell*potions[idx] >= success
//     }
// }


class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int m = spells.length;
        int n = potions.length;
        int max = 0;
        int[] res = new int[m];
        for(int i=0; i<n; i++){
            max = Math.max(max, potions[i]);
        }
        int[] map = new int[max+1];
        for(int i=0; i<n; i++){
            map[potions[i]]++;
        }
        int sum = 0;
        for(int i=max; i>=0; i--){
            sum+=map[i];
            map[i] = sum;
        }
        for(int i=0; i<m; i++){
            int spell = spells[i];
            long index = (success + spell -1)/spell;
            if(index <= max){
                res[i] = map[(int) index];
            } 
        }
        return res;
    }
}