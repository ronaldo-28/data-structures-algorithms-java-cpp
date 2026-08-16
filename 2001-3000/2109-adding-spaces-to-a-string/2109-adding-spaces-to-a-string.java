class Solution {
    public String addSpaces(String s, int[] spaces) {
        int n = s.length();
        char[] newChrs = new char[spaces.length + n];
        char[] sChr = s.toCharArray();

        int i = 0;
        int indx = 0;

        for(int spc : spaces){
            while(i < spc){
                newChrs[indx++] = sChr[i];
                i++;
            }
            newChrs[indx++] = ' '; 
        }

        while(i < n){
            newChrs[indx++] = sChr[i];
            i++;
        }

        return new String(newChrs);
    }
}