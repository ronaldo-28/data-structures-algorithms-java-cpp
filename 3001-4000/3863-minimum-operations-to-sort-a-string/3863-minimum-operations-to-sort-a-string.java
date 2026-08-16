class Solution {
    public int minOperations(String s) {
        int n = s.length();
        boolean ok = true;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i - 1) > s.charAt(i)) {
                ok = false;
                break;
            }
        }
        if (ok) {
            return 0;
        }
        if (n == 2) {
            return -1;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            cnt[s.charAt(i) - 'a']++;
        }
        int min = 0;
        while (cnt[min] == 0) {
            min++;
        }
        int max = 25;
        while (cnt[max] == 0) {
            max--;
        }
        char first = s.charAt(0);
        char last = s.charAt(n - 1);
        if (first == min + 'a' || last == max + 'a') {
            return 1;
        }
        if (last != min + 'a' || cnt[min] > 1) {
            return 2;
        }
        if (first != max + 'a' || cnt[max] > 1) {
            return 2;
        }
        return 3;
    }
}