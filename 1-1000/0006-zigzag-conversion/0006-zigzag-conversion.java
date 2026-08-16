class Solution {
    	public static String convert(String s, int numRows) {
		if (numRows == 1) return s;
		int inc = numRows * 2 - 2;
		int len = s.length();
		char[] res = new char[len];
        int idx = 0;
        for (int j = 0; j < len; j += inc)
            res[idx++] = s.charAt(j);
		for (int i = 1; i < numRows - 1; i++) {
			for (int j = i; j < len; j += inc) {
				res[idx++] = s.charAt(j);
				if (i != 0 && i != numRows - 1 && (j + inc - 2 * i) < len)
                    res[idx++] = s.charAt(j + inc - 2 * i);
			}
		}
        for (int j = numRows - 1; j < len; j += inc)
            res[idx++] = s.charAt(j);
		return new String(res);
    }
}