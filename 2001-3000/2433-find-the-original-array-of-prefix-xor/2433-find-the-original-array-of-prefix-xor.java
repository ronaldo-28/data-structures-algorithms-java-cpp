class Solution {
    static{
    for(int i = 0; i< 300;i++){
       findArray(new int[1]);
    }
}
    public static int[] findArray(int[] pref) {
        int[] arr = new int[pref.length];
        arr[0] = pref[0];
        for (int i = 1; i < pref.length; i++) {
            arr[i] = pref[i] ^ pref[i - 1];
        }
        return arr;
    }
}