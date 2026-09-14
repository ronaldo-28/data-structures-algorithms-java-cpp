class Solution {
    public int minFlips(String target) {
        int flips = 0;
        int prev = '0';
        for (int ch : target.getBytes()) {
            flips += (prev ^ ch);
            prev = ch;
        }
        return flips;
    }
}