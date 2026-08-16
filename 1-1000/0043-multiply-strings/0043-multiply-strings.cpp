class Solution {
public:
    string multiply(string num1, string num2) {
        if(num1=="0"||num2=="0") return "0";
        vector<int> arr(num1.size()+num2.size(),0);
        int carry = 0;
        int index = num1.size()+num2.size()-1;
        for(int i = num1.size()-1;i>=0;i--){
            int index2 = index;
            for(int j = num2.size()-1;j>=0;j--){
                
                int first = num1[i]-'0';
                int second = num2[j]-'0';
                int product = first*second;
                product+=carry+arr[index2];
                  carry = product/10;
                  int temp = product%10;
                  arr[index2] = temp;
                  index2--;          
            }
            if(carry>0) arr[index2] = carry;
            index--;
            carry=0;
        }
    int ind = -1;
    for(int i =0;i<arr.size();i++){
        if(arr[i]!=0){
            ind = i;
            break;
        }
    }
    string ans = "";
    while(ind<arr.size()){
        ans+=arr[ind]+'0';
        ind++;
    }
    return ans;
    }
};