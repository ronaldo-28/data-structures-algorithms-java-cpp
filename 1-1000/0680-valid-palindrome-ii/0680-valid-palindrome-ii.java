class Solution {
    // boolean f(String s) {
    //     int i = 0, j = s.length() - 1;
    //     while (i < j) {
    //         if (s.charAt(i) != s.charAt(j)) {
    //             return false;
    //         }
    //         i++;
    //         j--;
    //     }
    //     return true;
    // }

    // public boolean validPalindrome(String s) {
    //     int i = 0, j = s.length() - 1;

    //     while (i < j) {
    //         if (s.charAt(i) != s.charAt(j)) {
    //             StringBuilder t1 = new StringBuilder(s), t2 = new StringBuilder(s);
    //             t1.deleteCharAt(i);
    //             t2.deleteCharAt(j);

    //             return f(t1.toString()) || f(t2.toString());
    //         }

    //         i++;
    //         j--;
    //     }
    //     return true;
    // }

    public boolean validPalindrome(String s) {
		int n = s.length();
        byte[] cs = new byte[n];
        s.getBytes(0, n, cs, 0);
		for (int i = 0, j = cs.length - 1; i <= j; i++, j--) {
			if ((cs[i] != cs[j])) {
				boolean isPali = true;
				for (int x = i + 1, y = j; x <= y; x++, y--) {
					if ((cs[x] != cs[y])) {
						isPali = false;
						break;
					}
				}
				if (isPali) return true;

				for (int x = i, y = j - 1; x <= y; x++, y--) {
					if ((cs[x] != cs[y])) return false;
				}

				return true;
			}
		}

		return true;
	}
}