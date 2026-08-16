class Solution {
    public String longestDiverseString(int a, int b, int c) {
        int c_a=0,
            c_b=0,
            c_c=0;
        StringBuilder sb=new StringBuilder();
        while(true){
            if(a>0 &&(
                (c_a<2 && a>=b && a>=c)||
                (c_b==2 && b>=a && a>=c)||
                (c_c==2 && c>=a && a>=b)
            )){
                sb.append('a');
                c_a++;
                c_b=0;
                c_c=0;
                a--;
            }
            else if(b>0 &&(
                (c_b<2 && b>=a && b>=c)||
                (c_a==2 && a>=b && b>=c)||
                (c_c==2 && c>=b && b>=a)
            )){
                sb.append('b');
                c_b++;
                c_a=0;
                c_c=0;
                b--;
            }
            else if(c>0 &&(
                (c_c<2 && c>=b && c>=a)||
                (c_b==2 && b>=c && c>=a)||
                (c_a==2 && a>=c && c>=b)
            )){
                sb.append('c');
                c_c++;
                c_a=0;
                c_b=0;
                c--;
            }
            else{
                break;
            }
        }
        return sb.toString();
    }
}