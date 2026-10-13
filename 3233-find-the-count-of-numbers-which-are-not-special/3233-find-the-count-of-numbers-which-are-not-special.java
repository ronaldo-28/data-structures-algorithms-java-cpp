// squares of prime numbers
// find amount of primes from sqrt(l) to sqrt(r)

class Solution {
    public int nonSpecialCount(int l, int r) {
        int min = (int) Math.ceil(Math.sqrt(l));
        int max = (int) Math.floor(Math.sqrt(r));
        boolean[] seive = new boolean[max + 1];
        seive[1] = true;

        for(int i = 2; i * i <= max; i++) {
            if(seive[i]) {
                continue;
            }
            for(int j = i * 2; j <= max; j += i) {
                seive[j] = true;
            }
        }

        int primes = 0;
        for(int i = min; i <= max; i++) {
            if(!seive[i]) {
                primes++;
            }
        }

        return r - l + 1 - primes;
    }
}