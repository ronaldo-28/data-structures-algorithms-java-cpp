class Solution {
    public int minSizeSubarray(int[] n, int t) {
        int b=0;
        
        long x = 0;
        for(int i=0; i<n.length; i++)x+=n[i];

        if(x<=t){
            b+=n.length*(t/x);
            t%=(int)x;
        }

        if(t==0)return b;

        int[] a1=get(n, t);
        int[] a2=get(n, x-t);

        if(a1[0]>n.length&&a2[1]<0)return -1;
        else if(a1[0]>n.length)return b+n.length-a2[1];
        else if(a2[1]<0)return b+a1[0];
        else return b+Math.min(a1[0], n.length-a2[1]);
    }
    private int[] get(int[] n, long v){
        int[] a = new int[]{2_000_000_000, -2_000_000_000};
        long m=0;
        int j=0;
        for(int i=0; i<n.length; i++){
            for(; j<n.length&&m<v; j++)m+=n[j];
            if(m<v)return a;
            if(m==v){
                a[0]=Math.min(a[0], j-i);
                a[1]=Math.max(a[1], j-i);
            }
            m-=n[i];
        }
        return a;
    }
}