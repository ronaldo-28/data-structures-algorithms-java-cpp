class Solution {
    public boolean canAliceWin(int n) {
        int alice=10;
        int bob=alice;
        int found =0;
        while(n>=alice){
             found=0;
            if(n>=alice){
                n=n-alice;
               bob=alice-1;
               found=1;
               }
               if (n>=bob){
                n=n-bob;
                alice=bob-1;
                 found=2;
               }
        }
        if(found==0||found==2){
            return false;
        }
        return true ;
    }
}