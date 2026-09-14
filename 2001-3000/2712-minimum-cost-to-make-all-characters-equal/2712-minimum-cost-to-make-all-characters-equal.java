class Solution {
    public long minimumCost(String s) {
        char[] arr = s.toCharArray();
        long ans = 0;

        for(int i=1; i<arr.length; i++){
            if(arr[i] != arr[i-1]){
                int prefixCost = i;
                int suffixCost = arr.length-i;

                ans += Math.min(prefixCost, suffixCost);
            }
        }

        return ans;
    }
}