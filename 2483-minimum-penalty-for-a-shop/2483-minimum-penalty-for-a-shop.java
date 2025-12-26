class Solution {
    public int bestClosingTime(String customers) {
        byte[] cs = customers.getBytes(java.nio.charset.Charset.forName("ISO-8859-1"));
        int bestTime = -1;
        int customersLeft = 0;
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == 89) {
                customersLeft++;
                if (customersLeft > 0) {
                    bestTime = i;
                    customersLeft = 0;
                }
            } else {
                customersLeft--;
            }
        }

        return bestTime+1;
    }
}
