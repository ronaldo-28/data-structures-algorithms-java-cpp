class Solution {
    public List<Integer> circularPermutation(int n, int start) {
        int x=(2<<(n-1))-1;
        int c=0;
        List<Integer> a=new ArrayList<>();
        //a.add(x);
        int k=0,h=0;
        while(k<=x){
            h=k^(k>>1);
            if(h==start){
                break;
            }
            k++;
        }
        for(int i=k;i<=x;i++){
            c=i^(i>>1);
            a.add(c);
        }
        for(int i=0;i<k;i++){
            c=i^(i>>1);
            a.add(c);
        }
        return a;
    }
}