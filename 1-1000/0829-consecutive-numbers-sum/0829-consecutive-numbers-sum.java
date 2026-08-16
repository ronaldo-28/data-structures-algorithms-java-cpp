class Solution {
    public int consecutiveNumbersSum(int n) {
        while(n%2 == 0) n /= 2;
        int ans = 1;
        for(int oddNum = 3; oddNum*oddNum <= n; oddNum += 2){
            int exponent = 0;
            while(n%oddNum == 0){
                exponent++;
                n /= oddNum;
            }
            ans *= (exponent + 1);
        }
        if(n == 1){
            return ans;
        } else {
            return ans * 2;
        }
    }
}