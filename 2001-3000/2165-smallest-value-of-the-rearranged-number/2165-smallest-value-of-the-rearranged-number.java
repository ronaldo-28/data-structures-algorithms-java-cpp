class Solution {
    public long smallestNumber(long num) {
        if(num<=19 && num>=-11) return num;
        int[] count = new int[10];
        boolean neg = false;
        if(num<0){
            neg = true;
            num = -num;
        }
        long ans = 0;
        long temp = num;
        int small = 10;
        while(temp>0){
            int digit = (int)(temp%10);
            if(digit!=0) small = Math.min(small, digit);
            count[digit]++;
            temp/=10;
        }
        if(!neg){
            ans = small;
            count[small]--;
            for(int i=0;i<=9;i++){
                int c = count[i];
                for(int j=0;j<c;j++){
                    ans = ans*10 + i;
                }
            }
        }else{
            for(int i=9;i>=0;i--){
                int c = count[i];
                for(int j=0;j<c;j++){
                    ans = ans*10 + i;
                }
            }
            ans = -ans;
        }

        return ans;
    }
}