class Solution {
    public int racecar(int target) {
        return sequenceLength(target, false, 0, 0);
    }
    private int sequenceLength(int val, boolean isNeg, int p, int m) {
        if(is2PowerOneLess(val)) {
            if(m <= p) return numBits(val) + (2 * p);
            return numBits(val) + (2 * m) - 1; 
        }
        int pow2 = getNext2Power(val);
        int len1 = numBits(pow2) + sequenceLength(pow2 - val, !isNeg, p + (isNeg? 1: 0), m + (isNeg? 0: 1));
        pow2 >>= 1;
        int len2 = numBits(pow2) + sequenceLength(val - pow2, isNeg, p + (isNeg? 0: 1), m + (isNeg? 1: 0));
        return Math.min(len1, len2);
    }
    private int getNext2Power(int num) {
        int temp = num;
        while(!is2PowerOneLess(temp))
            temp = (temp | (temp >> 1));
        return temp;
    }
    private boolean is2PowerOneLess(int num) {
        return (num & (num + 1)) == 0;
    }
    private int numBits(int num) {
        int temp = num;
        int count = 0;
        while(temp > 0) {
            temp >>= 1;
            count++;
        }
        return count;
    }
}