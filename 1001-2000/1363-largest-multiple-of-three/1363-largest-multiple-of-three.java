class Solution {
    public String largestMultipleOfThree(int[] digits) {
        int N = digits.length;
        int[] count = new int[10];
        int sum = 0;
        for(int d : digits){
            count[d]++;
            sum += d;
        }
        int remain = sum % 3;
        // remove one digit if possible
        for(int d = 1; d < 9 && remain > 0; d++){
            if(count[d] > 0 && d % 3 == remain){
                remain = 0;
                count[d]--;
                N--;
            }
        }
        // remove two digit if remove one digit not possible
        if(remain == 1){
            remain += 3;
        }
        for(int d = 1; d < 9 && remain > 0; d++){
            if(d % 3 == 0){
                continue;
            }
            while(remain > 0 && remain >= d % 3 && count[d] > 0){
                remain -= d % 3;
                count[d]--;
                N--;
            }
        }

        if( remain > 0 || N == 0){
            return "";
        }

       // System.out.println("N=" + N + " count=" + Arrays.toString(count));

        char[] a = new char[N];
        int j = 0;
        for(int d = 9; d >= 0;  d--){
            while(count[d] > 0){
                //System.out.println("j=" + j + " d=" + d);
                a[j++] = (char)('0' + d);
                count[d]--;
            }
        }
        if(a[0] == '0'){
            return "0";
        }

        return new String(a);
    }
}