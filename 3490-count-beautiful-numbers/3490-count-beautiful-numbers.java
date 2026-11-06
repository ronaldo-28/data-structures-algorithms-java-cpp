class Solution {
	private static final int[][][][][] memo = new int[11][82][82][2][82];
	private static final boolean[][][][][] visited = new boolean[11][82][82][2][82];

	private static int solve(int rem, int currentSum, int productModS, boolean isLess, boolean isStarted, int targetS,
			int[] digits, int idx) {
		if (rem == 0)
			return (isStarted && currentSum == targetS && productModS == 0) ? 1 : 0;
		if (isLess && visited[rem][currentSum][productModS][isStarted ? 1 : 0][targetS])
			return memo[rem][currentSum][productModS][isStarted ? 1 : 0][targetS];
		int res = 0;
		int limit = isLess ? 9 : digits[idx];
		for (int d = 0; d <= limit; d++) {
			boolean nextIsLess = isLess || (d < limit);
			if (!isStarted && d == 0)
				res += solve(rem - 1, 0, 0, nextIsLess, false, targetS, digits, idx + 1);
			else {
				int nextSum = currentSum + d;
				if (nextSum <= targetS) {
					int nextProdModS;
					if (!isStarted)
						nextProdModS = d % targetS;
					else
						nextProdModS = (productModS * d) % targetS;
					res += solve(rem - 1, nextSum, nextProdModS, nextIsLess, true, targetS, digits, idx + 1);
				}
			}
		}
		if (isLess) {
			visited[rem][currentSum][productModS][isStarted ? 1 : 0][targetS] = true;
			memo[rem][currentSum][productModS][isStarted ? 1 : 0][targetS] = res;
		}
		return res;
	}

	private static int count(int x) {
		if (x <= 0)
			return 0;
		char[] chars = Integer.toString(x).toCharArray();
		int n = chars.length;
		int[] digits = new int[n];
		for (int i = 0; i < n; i++)
			digits[i] = chars[i] - '0';
		int totalBeautiful = 0;
		for (int targetS = 1; targetS <= 81; targetS++)
			totalBeautiful += solve(n, 0, 0, false, false, targetS, digits, 0);
		return totalBeautiful;
	}

	public static int beautifulNumbers(int l, int r) {
		return count(r) - count(l - 1);
	}
}