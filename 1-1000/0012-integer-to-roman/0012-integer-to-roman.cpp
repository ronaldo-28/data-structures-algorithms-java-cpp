class Solution {
public:
    string intToRoman(int num) {
        int arr[]={1000,900,500,400,100,90,50,40,10,9,5,4,1};
        string ch[]={"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        string str="";
        int i=0;
        while(num>0){
        if(num>=arr[i]){
            int n=num/arr[i];

            num=num%arr[i];

            while(n>0){
                str+=ch[i];
                n--;
            }
        }

        i++;


        }
        return str;
    }
};