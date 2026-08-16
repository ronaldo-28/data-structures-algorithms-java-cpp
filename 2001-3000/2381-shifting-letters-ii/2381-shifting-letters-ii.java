class Solution {
    public String shiftingLetters(String s, int[][] shifts) {
        int n = s.length();
        int arr[] = new int[n+1];
        for(int shift[]:shifts){
            int x = shift[0];
            int y = shift[1];
            int delta = shift[2]==1?1:-1;
            arr[x] += delta;
            arr[y+1] -= delta;
        }
        for(int i=1;i<n;i++) arr[i] += arr[i-1];
        char[] ch = new char[n];
        for(int i=0;i<n;i++){
            int val = (s.charAt(i)-'a'+arr[i])%26;
            if(val<0) val+=26;
            ch[i] = (char)(val+'a');
        }
        return new String(ch);
    }
}