class Solution {
    public String fractionAddition(String expression) {
        int n = expression.length();
        int tot = 3628800;

        long ans = 0;
        int i = 0;

        while(i < n){
            int res = tot;

            if(expression.charAt(i) == '-'){
                res *= -1;
                i++;
            }
            else if(expression.charAt(i) == '+'){
                i++;
            }

            if(expression.charAt(i+1) == '0'){
                res *= 10;
                i++;
            }
            else{
                res *= (expression.charAt(i) - '0');
            }
            
            i += 2;

            if(i+1 < n && expression.charAt(i+1) == '0'){
                res /= 10;
                i++;
            }
            else{
                res /= (expression.charAt(i) - '0');
            }

            i++;
            ans += res;
        }

        for(i=10; i>=1; i--){
            if(ans%i == 0){
                ans /= i;
                tot /= i;
            }
        }

        while(ans%2 == 0 && tot%2 == 0){
            ans /= 2;
            tot /= 2;
        }

        while(ans%3 == 0 && tot%3 == 0){
            ans /= 3;
            tot /= 3;
        }

        while(ans%5 == 0 && tot%5 == 0){
            ans /= 5;
            tot /= 5;
        }

        while(ans%7 == 0 && tot%7 == 0){
            ans /= 7;
            tot /= 7;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(ans).append('/').append(tot);

        return sb.toString();
    }
}