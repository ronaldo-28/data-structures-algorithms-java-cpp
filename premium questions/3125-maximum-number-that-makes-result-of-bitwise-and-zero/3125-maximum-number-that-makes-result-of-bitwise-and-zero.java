class Solution {
    public long maxNumber(long n) {
        long num=1;

        //finding MSB
        while((num<<1)<=n) {
            num<<=1;
        }
        //substract 1 from MSB
        return num-1;
    }
}