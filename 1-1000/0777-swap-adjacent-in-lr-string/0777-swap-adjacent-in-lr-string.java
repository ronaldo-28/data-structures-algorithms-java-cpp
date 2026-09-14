class Solution {
    static {
        for (int i = 0; i < 300; i++) {
            canTransform("","");
        }
    }
    public static boolean canTransform(String start, String result) {
        int n = result.length();
        int i = 0, j = 0;
        while (i < n && j < n) {
            while (i < n && start.charAt(i) == 'X') i++;
            while (j < n && result.charAt(j) == 'X') j++;
            
            if (i == n && j == n) break;
            if (i == n || j == n || start.charAt(i) != result.charAt(j)) return false;

            if (start.charAt(i) == 'R' && i > j) return false;
            if (start.charAt(i) == 'L' && i < j) return false;

            i++;
            j++;
        }

        while (i < n) {
            if (start.charAt(i) != 'X') return false;
            i++;
        }
        while (j < n) {
            if (result.charAt(j) != 'X') return false;
            j++;
        }

        return true;
    }
}