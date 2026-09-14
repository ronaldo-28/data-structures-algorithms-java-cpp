class Solution {
    static final boolean print = false;
    public boolean isValid(String code) {
        char[] cs = code.toCharArray();
        Deque<String> nesting = new ArrayDeque<>();
        boolean reachedEndOfString = false;
        for (int i = 0; i < cs.length;) {
            if (cs[i] != '<') {
                if (cs[i] != ' ' && nesting.isEmpty()) {
                    if (print) System.out.println("Expected the character at i="+i+", which is a '"+cs[i]+"', to be wrapped in a tag.");
                    return false;
                }
                i++;
                continue;
            }
            if (i == cs.length - 1) {
                if (print) System.out.println("Found a tag start at i='end of string minus one'.");
                return false;
            }
            if (cs[i + 1] == '!') {
                if (i + 8 >= cs.length) {
                    if (print) System.out.println("CDATA tag out of range.");
                    return false;
                }
                String cdataStart = new String(cs, i, 9);
                if (!cdataStart.equals("<![CDATA[")) {
                    if (print) System.out.println("Expected a CDATA tag at i="+i+". Found '"+cdataStart+"' instead.");
                    return false;
                }
                if (nesting.isEmpty()) {
                    if (print) System.out.println("CDATA tag starting at i="+i+" is not enclosed in a tag.");
                    return false;
                }
                int end = i + 8;
                while (end < cs.length - 2 && !new String(cs, end, 3).equals("]]>")) end++;
                if (end == cs.length - 2) {
                    if (print) System.out.println("Failed to find an end for the CDATA tag starting at i="+i+".");
                    return false;
                }
                i = end + 3;
                continue;
            }
            int end = i + 1;
            while (end < cs.length && cs[end] != '>') end++;
            if (end == cs.length) {
                if (print) System.out.println("Reached end of line while parsing the tag starting at i="+i+".");
                return false;
            }
            if (cs[i + 1] == '/') {
                int length = end - i - 2;
                if (length > 9) {
                    if (print) System.out.println("Closing tag starting at i="+i+" is too long.");
                    return false;
                }
                if (length == 0) {
                    if (print) System.out.println("Closing tag starting at i="+i+" is empty.");
                    return false;
                }
                String tag = new String(cs, i + 2, length);
                if (!isUppercase(tag)) {
                    if (print) System.out.println("Closing tag starting at i="+i+" doesn't contain only uppercase letters. Tag was: '"+tag+"'.");
                    return false;
                }
                if (nesting.isEmpty()) {
                    if (print) System.out.println("Found a closing tag starting at i="+i+". Didn't expect any closing. Tag was: '"+tag+"'.");
                    return false;
                }
                if (!nesting.getLast().equals(tag)) {
                    if (print) System.out.println("Found a closing tag starting at i="+i+" which doesn't properly close the preceding tag of '"+nesting.getLast()+". Tag was: '"+tag+"'.");
                    return false;
                }
                nesting.removeLast();
                reachedEndOfString = nesting.isEmpty();
                i = end + 1;
                continue;
            }
            int length = end - i - 1;
            if (length > 9) {
                if (print) System.out.println("Opening tag starting at i="+i+" is too long.");
                return false;
            }
            if (length == 0) {
                if (print) System.out.println("Opening tag starting at i="+i+" is empty.");
                return false;
            }
            String tag = new String(cs, i + 1, length);
            if (!isUppercase(tag)) {
                if (print) System.out.println("Opening tag starting at i="+i+" doesn't contain only uppercase letters. Tag was: '"+tag+"'.");
                return false;
            }
            if (reachedEndOfString) {
                if (print) System.out.println("Already re-reached a nesting depth of 0. i="+i+" seems to be the start of *another* string. Invalid.");
                return false;
            }
            nesting.addLast(tag);
            i = end + 1;
        }
        if (!nesting.isEmpty()) {
            if (print) System.out.println("Tags were left unclosed. Nesting stack: "+nesting+".");
            return false;
        }
        if (print) System.out.println("String is valid!");
        return true;
    }
    private boolean isUppercase(String s) {
        for (char c : s.toCharArray())
            if (c < 'A' || c > 'Z') return false;
        return true;
    }
}
// class Solution {
//     public boolean isValid(String code) {
//         return isValid(0, code.length() - 1, code.toCharArray());
        
//     }
//     public boolean isValid(int l, int r, char[] cs) {
//         if (r - l < 6) return false;
//         if (cs[0] != '<' || cs[cs.length - 1] != '>') return false; // free checks.
//         int i = l + 1;
//         while (i <= r && cs[i] != '>' && cs[i] >= 'A' && cs[i] <= 'Z') i++;
//         if (i > r || cs[i] != '>') return false;
//         String startTag = new String(cs, l + 1, i - (l + 1));
//         if (startTag.length() > 9 || startTag.length() == 0) return false;
//         System.out.println("Start tag of "+l+"<->"+r+" is "+startTag);
//         int endTagStart = r - startTag.length();
//         String endTag = new String(cs, endTagStart, startTag.length());
//         System.out.println("Corresponding end tag: "+endTag);
//         if (!startTag.equals(endTag)) return false;
//         return true;
//     }
// }