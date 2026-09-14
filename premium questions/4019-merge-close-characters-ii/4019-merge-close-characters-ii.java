class Solution {
    static char[] stack = new char[(int)5e5 + 1];
    static int[] last = new int[26];
    public String mergeCharacters(String s, int k) {

        char[] cs = s.toCharArray();

        int top = -1;
        Arrays.fill(last, -(k + 1));

        for(char c:cs){
            if(top - last[c - 'a'] < k) continue;
            stack[++top] = c;
            last[c -'a'] = top;
        }
        
        return new String(stack, 0, top + 1);
    }
}