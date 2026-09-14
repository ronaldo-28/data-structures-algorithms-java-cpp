class Solution {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        // Calculate the number of rounds available.
        int rounds = minutesToTest / minutesToDie;
        
        // Determine the minimal number of pigs required.
        int pigs = 0;
        while (Math.pow(rounds + 1, pigs) < buckets) {
            pigs++;
        }
        return pigs;
    }
}
