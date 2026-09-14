class Solution {
    static {
        String[] arr = new String[1];
        for (int i = 0; i < 500; i++){
            judgeCircle("");
        }
    }
    public static boolean judgeCircle(String moves) {
        int[] freq = new int[26];
        char[] move = moves.toCharArray();
        if(move.length % 2 != 0) return false;
        for(char ch : move){
            freq[ch - 'A']++;
        }
        if(freq['U' - 'A'] == freq['D' - 'A'] && freq['L' - 'A'] == freq['R' - 'A']) return true;
        return false;
    }
}