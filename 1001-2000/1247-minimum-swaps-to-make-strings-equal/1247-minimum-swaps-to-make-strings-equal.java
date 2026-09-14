class Solution {
    public int minimumSwap(String s1, String s2) {
        int n1=s1.length();
        int n2=s2.length();
        if(n1!=n2){
            return -1;
        }
        int xy=0,yx=0;
        for(int  i=0;i<n1;i++){
            char c1=s1.charAt(i);
            char c2=s2.charAt(i);
            if(c1=='x'&& c2=='y'){
                xy++;
            }
            else if(c1=='y'&& c2=='x'){
                yx++;
            }
        }
        int tot=xy+yx;
        if(tot%2!=0){
            return -1;
        }
        return (xy)/2+(yx)/2+((xy)%2)*2;
    }
}