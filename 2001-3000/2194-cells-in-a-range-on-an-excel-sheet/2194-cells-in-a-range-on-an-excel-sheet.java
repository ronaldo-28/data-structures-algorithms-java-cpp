class Solution {
    public List<String> cellsInRange(String s) {
        // BETTER SOLUTION
        char rowStart = s.charAt(0), rowEnd = s.charAt(3), colStart = s.charAt(1), colEnd = s.charAt(4);
        List<String> result = new ArrayList();
        for (char i = rowStart; i <= rowEnd; i++) {
            for (char j = colStart; j <= colEnd; j++) {
                result.add(new String(new char[] {i, j}));
            }
        }
        return result;

        // List<String> ans = new ArrayList<>();
        // for (char c = s.charAt(0); c <= s.charAt(3); c++) {
        //     for (char r = s.charAt(1); r <= s.charAt(4); r++) {
        //         ans.add("" + c + r);
        //     }
        // }
        // return ans;
    }
}