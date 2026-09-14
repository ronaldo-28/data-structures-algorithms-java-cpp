class Solution {
    public static int totalMoney(int n) {
        int w = n/7;
        int r = n % 7;
        int sumFull = 28*w + 7*(w*(w-1)/2);
        int SumRem = r*w + r*(r+1)/2;
        return sumFull + SumRem;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(totalMoney(n));
    }
}