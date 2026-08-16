class Solution {
    public boolean checkIfExist(int[] arr) {
        boolean[] seen = new boolean[2001];

        for (int i=0; i<arr.length; i++) {
            if (arr[i]*2 >= -1000 && arr[i]*2 <= 1000 && seen[arr[i] * 2 + 1000]) return true;
            if (arr[i]%2==0 && seen[arr[i]/2 + 1000]) return true;
            seen[arr[i] + 1000] = true;
        }
        return false;
    }
}