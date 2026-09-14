class Solution {
public:
    int findIntegers(int num) {
         if(num == 0) return 1;
         if(num == 1) return 2; 
        
         //STEP 1         
         int digit = 0;
         while(num >= 1 << digit) digit++;
         
         int *digitsOfNum = new int[digit];
         int *zero = new int[digit];
         int *one = new int[digit];
         
         //not include 0 in DP
         zero[0] = 0;
         zero[1] = 1;
         one[0] = 1;
         one[1] = 0;
         
         int sum = 1; //include 0
         
         for(int i = 0; i < digit; i++)
         {
             if(i > 1) 
             {
                 zero[i] = zero[i - 1] + zero[i - 2];
                 one[i] = one[i - 1] + one[i - 2];
             }
             if(i < digit - 1) sum += zero[i] + one[i];
             digitsOfNum[digit - 1 - i] = num % 2;
             num >>= 1;
         }        
         
         // STEP 2
         
         int i = 0;
         bool isValid = true;
         
         while(true)
         {
             if(i + 1 == digit)break;
             if(isValid)
             {
                 if(digitsOfNum[i] != digitsOfNum[i + 1])
                 {
                     zero[i + 1] = zero[i] + one[i];
                     one[i + 1] = zero[i];
                     i++;
                 }else{
                     if(digitsOfNum[i] == 1)
                     {
                         isValid = false;
                     }else{
                         zero[i + 1] = zero[i] + one[i];
                         one[i + 1] = zero[i] - 1;
                         i++;
                     }
                 }
             }else{
                 zero[i + 1] = zero[i] + one[i];
                 one[i + 1] = zero[i];
                 i++;
             }
         }
         
         return sum + zero[digit - 1] + one[digit - 1];
    }
};