class Solution {
    public void duplicateZeros(int[] arr) {
        int[] check = arr.clone();
        int f = 0;
        int s = 0;
        while (f < arr.length) {
            arr[f] = check[s];
            if (check[s] == 0) {
                if (f + 1 < arr.length) arr[++f] = 0;
            }
            s ++;
            f ++;
        }
    }
}