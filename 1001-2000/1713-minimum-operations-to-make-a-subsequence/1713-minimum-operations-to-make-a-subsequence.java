class Solution {
    public int minOperations(int[] tr,int[] ar){
        Map<Integer,Integer> mp=new HashMap<>();
        for(int i=0;i<tr.length;i++)mp.put(tr[i],i);
        int[] ls=new int[tr.length];
        int si=0;
        for(int num:ar){
            Integer ix=mp.get(num);
            if(ix==null)continue;
            int l=0,r=si;
            while(l<r){
                int md=(l+r)>>>1;
                if(ls[md]<ix)l=md+1;
                else r=md;
            }
            ls[l]=ix;
            if(l==si)si++;
        }
        return tr.length-si;
    }
}