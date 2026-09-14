class Solution {
    public String stringShift(String s, int[][] shift) {
        int netShift = 0, len = s.length();
        for (var sh : shift) {
            netShift += sh[0] == 0 ? -sh[1] : sh[1]; //left shift is negative, right is positive
        }
        netShift %= len;
        var split = netShift > 0 ? len - netShift : Math.abs(netShift);
        return s.substring(split) + s.substring(0, split);
    }
}