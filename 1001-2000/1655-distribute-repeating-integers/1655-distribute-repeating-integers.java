class Solution {
    public boolean canDistribute(int[] nums, int[] quantity) {
        // Counting the frequency of each item
        int[] tmp=new int[1001];
        for(int n: nums) tmp[n]++;
        // removing items with frequency 0 to enhance performance
        int nonZeroCount=0;
        for(int f: tmp) if(f!=0) nonZeroCount++;
        int[] fa=new int[nonZeroCount];
        for(int f: tmp) if(f!=0) fa[--nonZeroCount]=f;

        // This was not originally in my code and makes all
        // the difference in the world
        Arrays.sort(quantity);
        // It is important to decrease the index so we process
        // bigger items first
        return helper(quantity.length-1,fa,quantity);
    }
    boolean helper(int in, int[] fa, int[] quantity) {
        if(in==-1) return true;
        int need=quantity[in];
        for(int i=fa.length-1;i>-1;i--) {
            if(fa[i]<need||i>0&&fa[i]==fa[i-1]) continue;
            fa[i]-=need;
            // Notice that we decrease the index so to process
            // bigger values first
            if(helper(in-1,fa,quantity)) return true;
            fa[i]+=need;
        }
        return false;
    }
}