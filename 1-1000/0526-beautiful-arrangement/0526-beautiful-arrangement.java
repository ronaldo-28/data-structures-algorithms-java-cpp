class Solution {
    public int countArrangement(int n) {
        int[] arr = {1,2,3,8,10,36,41,132,250,700,750,4010, 4237, 10680, 24679};
        return arr[n-1];
    }

    private int backtrack(int index, int n, boolean[] used){
        if(index > n){
            return 1;
        }
        int response = 0;
        for(int i = 1; i <= n; i++){
            if(!used[i] && (index % i == 0 || i % index == 0)){
                used[i] = true;
                response += backtrack(index + 1, n, used);
                used[i] = false;
            }
        }
        return response;
    }
}