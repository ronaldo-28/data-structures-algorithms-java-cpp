class Solution {
    public int maxConsecutive(int bottom, int top, int[] special) {
        int minii = special[0];
        int maxii = special[0];
        int n = special.length;
        for(int x :special) {
           minii = Math.min(x, minii);
           maxii = Math.max(x, maxii);
        }
        if (top==bottom) {
            return 0;
        }
        //System.out.println(maxii + " : " + minii);
        //System.out.println(Math.ceil((maxii-minii)/n-1));
        int bucketSize = Math.max(1,(int)Math.ceil((maxii-minii)/n-1));
        int buckets = ((maxii-minii)/bucketSize)+1;
        int[] maxi = new int[buckets];
        int[] mini = new int[buckets];
        Arrays.fill(maxi, Integer.MIN_VALUE);
        Arrays.fill(mini, Integer.MAX_VALUE);
        //System.out.println(buckets + " : " + bucketSize);
        for(int x : special) {
            int b = (x-minii)/bucketSize;
            maxi[b] = Math.max(maxi[b], x);
            mini[b] = Math.min(mini[b], x);
        }

        //System.out.println(Arrays.toString(maxi));
        //System.out.println(Arrays.toString(mini));
        int ans = 0;
        int prev= maxi[0];
        for(int i=1; i<buckets; i++) {
            if (mini[i]==Integer.MAX_VALUE) {
                continue;
            }
            ans = Math.max(ans, mini[i]-prev-1);
            prev = maxi[i];
        }
        //System.out.println(ans);
        return Math.max(ans, Math.max(minii-bottom, top-maxii));
    }
}