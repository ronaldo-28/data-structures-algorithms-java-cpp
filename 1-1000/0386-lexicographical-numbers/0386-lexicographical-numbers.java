class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> k = new ArrayList<>();
        for(int i=1;i<=n && i<10;i++){
            k.add(i);
            nextOrder(k,i*10,n);
        }
        return k;
    }
    public static void nextOrder(List<Integer> k,int val,int n){
        if(val>n) return;
        for(int i=0;i<10&&val+i<=n;i++){
            val = val+i;
            k.add(val);
            nextOrder(k,val*10,n);
            val = val - i;
        }
    }
}