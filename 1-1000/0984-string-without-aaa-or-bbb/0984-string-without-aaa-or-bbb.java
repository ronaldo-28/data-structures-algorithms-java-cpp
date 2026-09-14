class Solution {
    public String strWithout3a3b(int a, int b) {
        StringBuilder sb = new StringBuilder(a+b);

        while(a>0|| b>0){
            if(b>a){
                if(b>=2){
                    sb.append("bb");
                    b=b-2;
                    if(a>0){
                    sb.append("a");
                    a=a-1;
                    }
                }
                else{
                    sb.append("b");
                    b--;
                }
            }
            else if(a==b){
                sb.append("a");
                a--;
                sb.append("b");
                b--;
            }
            else{
                if(a>=2){
                    sb.append("aa");
                    a=a-2;
                    if(b>0){
                    sb.append("b");
                    b=b-1;
                    }
                }
                else{
                    sb.append("a");
                    a--;
                }

            }
        }
     return sb.toString();   
    }
}