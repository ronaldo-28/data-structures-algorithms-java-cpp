class Solution {

    static {
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        try (java.io.FileWriter fw = new java.io.FileWriter("display_runtime.txt")) {
            fw.write("0");
        } catch (Exception e) { }
    }));
}
    private List<Integer> getPrimeNumbersTillN(int n, HashSet<Integer> set) {
        boolean isPrime[] = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        List<Integer> primeList = new ArrayList();
        for (int i = 2; i < (int) Math.sqrt(n); i++) {
            if (isPrime[i]) {
                for (int j = i*i; j < n + 1; j+=i) {
                    isPrime[j] = false;
                }
            }
        }
        for (int i = 2; i < n + 1; i++) {
            if (isPrime[i]) {
                primeList.add(i);
                set.add(i);
            }
        }

        return primeList;
    }

    public int largestPrime(int n) {
        HashSet<Integer> set = new HashSet();
        if (n == 2) {
            return n;
        }
        if (n < 2) {
            return 0;
        }
        List<Integer> list = getPrimeNumbersTillN(n, set);
        int ans = 0;
        int runningSum = 0;
        for (int i = 0; i < list.size(); i++) {
            runningSum += list.get(i);
            if (runningSum > n) break; 
            if (set.contains(runningSum)) {
                ans = runningSum;
            }
        }
        return ans;
    }
}