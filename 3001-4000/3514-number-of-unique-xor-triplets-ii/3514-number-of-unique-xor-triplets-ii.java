import java.util.*;
class Solution {
    private void fwt(long[] a, boolean invert){
        int n = a.length;
        for(int len = 1; 2 * len <= n; len *= 2){
            for(int i = 0; i <n; i +=2 * len){
                for(int j = 0; j < len; j++){
                    long u = a[i+j];
                    long v = a[i+j+len];
                    a[i+j] = u + v;
                    a[i+j+len] = u-v;
                }
            }
        }
        if(invert){
            for(int i = 0; i < n; i++){
                a[i] /= n;
            }
        }
    }
    public int uniqueXorTriplets(int[] nums) {
        int[] glarn = nums;
        int size = 2048;
        long[] f = new long[size];
        for(int num: glarn){
            f[num] = 1;
        }
        fwt(f, false);
        for(int i = 0; i < f.length; i++){
            f[i] = f[i] * f[i] * f[i];
        }
        fwt(f, true);
        int count = 0;
        for(long val: f){
            if(val > 0){
                count++;
            }
        }
        return count;
                }
            }