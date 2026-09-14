class Solution {
    public boolean sumOfNumberAndReverse(int num) {
        if (num == 0) {return true;}
        
        int digit = digit(num);
        int firstDigit = num / (int)Math.pow(10, digit - 1);
        
        if (firstDigit == 1) {
            return  hasAns(0, num, getPoly(digit)) || hasAns(0, num, getPoly(digit - 1));
        }
        return hasAns(0, num, getPoly(digit));
    }
    
    public int[] getPoly(int digit) {
        int[] poly = new int[(digit + 1) /2];
        for (int i = 0; i < poly.length; i++) {
            poly[i] = (int)Math.pow(10, digit - 1 - i*2) + 1;
        }
        return poly;
    }
    
    public boolean hasAns(int polyIdx, int num,int[] poly) {
        if (num == 0) {return true;}
        if (num < 0 || polyIdx == poly.length) {return false;}
        
        int curPoly = poly[polyIdx];
        int polyLastDigit = curPoly % 10;
        
        int curDigit = num % 10;
        if (curDigit % polyLastDigit != 0) {return false;}
    
        int polyMultiplyer = curDigit / polyLastDigit;
        boolean res = false;
        if (polyIdx == 0 && polyMultiplyer == 0) {
            res = false;
        } else {
            res = hasAns(polyIdx+1, (num - (curPoly * polyMultiplyer)) / 10, poly);
        }
        
        if (res) {return true;}
        
        polyMultiplyer += 10 / polyLastDigit;
        if (polyMultiplyer <= 18) {
            return hasAns(polyIdx+1, (num - (curPoly * polyMultiplyer)) / 10, poly);
        }
        return false;
    }
    
    public int digit(int num) {
        int res = 0;
        while (num > 0) {
            res++;
            num /= 10;
        }
        return res;
    }
}