class Solution {
	private static final int MODULUS = (int) 1e9 + 7;
	private static final long MOD_LONG = MODULUS;

	private static int add(int x, int y) {
		return (x + y) % MODULUS;
	}

	private static int subtract(int x, int y) {
		return (x + MODULUS - y) % MODULUS;
	}

	private static int multiply(int x, int y) {
		return (int) ((long) x * y % MOD_LONG);
	}

	public static int numberOfStableArrays(int zero, int one, int limit) {
		int m = Math.max(zero, one);
		int[][] sumlim = new int[m + 1][m + 1]; // [count][sum]
		int[] ps = new int[m + 1];
		int[] sl = sumlim[0];
		sl[0] = 1;
		for (int c = 1; c <= m; c++) {
			int e = Math.min(c * limit, m);
			int s = 0;
			for (int i = 0; i <= e; i++) {
				ps[i] = s;
				s = add(s, sl[i]);
			}
			sl = sumlim[c];
			for (int i = c; i <= e; i++)
				sl[i] = i < limit ? ps[i] : subtract(ps[i], ps[i - limit]);
		}
		int result = m <= limit ? 2 : 0;
		int c0 = sumlim[1][zero];
		int c1 = sumlim[1][one];
		for (int i = 2; i <= m; i++) {
			int c0p = c0;
			int c1p = c1;
			c0 = sumlim[i][zero];
			c1 = sumlim[i][one];
			result = add(result, multiply(c0, c1p));
			result = add(result, multiply(c1, c0p));
			int t = multiply(c0, c1);
			result = add(result, add(t, t));
		}
		return result;
	}
}