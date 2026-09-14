class Solution {
    public boolean areNumbersAscending(String s) {
        int last = 0, num = 0;
        for (char c : s.toCharArray()){
            if (Character.isDigit(c)){
                num = num*10 + (c - '0');
            }

            else{
                if (num == 0) continue;
                if (num <= last) return false;
                last = num;
                num = 0;
            }
        }
        if (num != 0 && num <= last) return false;
        return true;
    }
}