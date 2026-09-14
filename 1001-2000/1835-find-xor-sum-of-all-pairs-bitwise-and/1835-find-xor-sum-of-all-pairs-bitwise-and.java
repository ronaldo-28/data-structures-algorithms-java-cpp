class Solution {
    public int getXORSum(int[] arr1, int[] arr2) {
    int xor1=fn(arr1);
    int xor2=fn(arr2);
    return xor1 & xor2;
    }
    public int fn(int[]arr){
        int xor=0;
        for(int x:arr){
xor^=x;
        }
        return xor;
    }
}