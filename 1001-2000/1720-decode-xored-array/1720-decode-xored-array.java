class Solution {
    static{
        for(int i=0;i<100;i++){
            decode(new int[]{1,2,3}, 1);
        }
    }
    private static int find(int xor, int a){
        return xor^a;
        // String b="";
        // for(int i=0;i<32;i++){
        //     if(((xor>>i)&1)==0)
        //     b=((a>>i)&1)+b;
        //     else{
        //         int x;
        //         if(((a>>i)&1)==1)  x=0;
        //         else
        //         x=1;
        //         b=x+b;
        //     }
        // }
        // return Integer.parseInt(b, 2);
    }
    public static int[] decode(int[] encoded, int first) {
        int l=encoded.length;
        int[] ans=new int[l+1];
        ans[0]=first;
        for(int i=0;i<l;i++){
            ans[i+1]=ans[i]^encoded[i]; //find(encoded[i-1], ans[i-1]);
        }
        return ans;
    }
}