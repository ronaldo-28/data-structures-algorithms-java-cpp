class Solution {
    public int getLucky(String s, int k) {
        int sum = 0;
        for(int i = 0;i<s.length();i++){
            sum += sum(s.charAt(i)-'a'+1);
        }
        for(int i = 0;i<k-1;i++){
            sum = sum(sum);
        }
        return sum;
    }
    int sum(int n){
        int c = 0;
        while(n!=0){
            c+=n%10;
            n/=10;
        }
        return c;
    }
}