class Solution {
    public String smallestNumber(String pattern) {
        int n = pattern.length();
        char[] ans = new char[n+1];
        for (int i=0; i<=n; i++){
            ans[i] = ((i+1) + "").charAt(0);
        }
        for (int i=0; i<n; i++){
            int j=i;
            while(j>=0 && pattern.charAt(j) == 'D'){
                swap(ans,j,j+1);
                j--;
            }
        }
        return String.valueOf(ans);
    }
    private static void swap(char[]arr, int i , int j){
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}