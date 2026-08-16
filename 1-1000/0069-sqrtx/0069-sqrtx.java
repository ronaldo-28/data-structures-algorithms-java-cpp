import java.lang.Math;
class Solution {
    public int mySqrt(int x) {
        
        double mydouble = (double) x;
        double wurzel = Math.sqrt(mydouble);
        int abgerundet = (int) wurzel;
        return abgerundet;
    }
     
}