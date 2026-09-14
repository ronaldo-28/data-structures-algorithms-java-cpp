class Solution {
    public int smallestValue(int n) {
        while(true){
            int sum = sumOfPrimeFactors(n);
            if(sum==n){
                return n;
            }
            n = sum;
        }
    }
    int sumOfPrimeFactors(int n){
        int temp = n;
        int sum = 0;
        for(int i=2; (long)i*i<=temp; i++){
            while(temp%i==0){
                sum += i;
                temp /= i;
            }
        }
        if(temp>1) sum+=temp;
        return sum;
    }
}