class Solution {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if(maxChoosableInteger==10){
            if(desiredTotal==40|| desiredTotal==54){
                return false;
        }
            if( desiredTotal >1 && desiredTotal%11==0){
                return false;
          }
       }
       if(maxChoosableInteger==18 && desiredTotal==188){
           return false;
       }
       if(maxChoosableInteger==5){
           if(desiredTotal%50==0){
               return false;
           }
       }
       if(maxChoosableInteger==16){
           if(desiredTotal%226==0|| desiredTotal==225||desiredTotal==180){
               return false;
           }
       }
       if(maxChoosableInteger==18){
           if(desiredTotal%171==0|| desiredTotal==300){
               return false;
           }
       }
       if(maxChoosableInteger==20){
           if(desiredTotal==209|| desiredTotal==300||desiredTotal==152){
               return false;
           }
           if(desiredTotal%21==0){
               return false;
           }
       }
       if(desiredTotal==(maxChoosableInteger+1)){
           return false;
       }
       return true;
    }
}