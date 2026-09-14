class Solution {
    string fun(int num,int extra) {
        if(num==0) return "";
        string a[] = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
        "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        string tens[] = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        string thousand = "Thousand";
        string result;
        if (num < 20) result+= a[num];
        else if (num < 100) result+= tens[num / 10 - 2] + (num % 10 ? " " + a[num % 10] : "");
        else result+= a[num / 100] + " Hundred" + (num % 100 ? " " + numberToWords(num % 100) : "");
        if(extra==0) return result;
        else if(extra==1) return result+" "+"Thousand";
        else if(extra==2) return result+" "+"Million";
        else return result+" "+"Billion";
    }

public:
    string numberToWords(int num) {
        if(num==0) return "Zero";
        int extra=0;
        string ans;
        while(num>0){
            string s=fun(num%1000,extra);
            num/=1000;
            extra++;
            if(s=="") continue;
            ans = s + " " + ans;
        }
        ans.pop_back();
        return ans;
    }
};