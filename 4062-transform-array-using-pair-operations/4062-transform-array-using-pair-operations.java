class Solution {
    public boolean canTransform(int[] source, int[] target) {
       long sum1 = 0;
       long sum2 = 0;
       for(int i=0;i<source.length;i++){
        sum1+=source[i];
        sum2+=target[i];
       }
       return sum1 == sum2;
    }
}