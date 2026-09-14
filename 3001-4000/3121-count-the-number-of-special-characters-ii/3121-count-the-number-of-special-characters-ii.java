class Solution {
    public int numberOfSpecialChars(String word) {
		int n = word.length();
		char[] chars = word.toCharArray();
		int[] lastLower = new int[26];
		int[] firstUpper = new int[26];
		Arrays.fill(lastLower, -1);
		Arrays.fill(firstUpper, -1);
		for (int i = 0; i < n; i++) {
			if (chars[i] >= 'a') {
				lastLower[chars[i] - 'a'] = i;
			} else if (firstUpper[chars[i] - 'A'] == -1) {
				firstUpper[chars[i] - 'A'] = i;
			}
		}
		int ans = 0;
		for (int i = 0; i < 26; i++) {
			if (lastLower[i] != -1 && lastLower[i] < firstUpper[i]) {
				ans++;
			}
		}
		return ans;
	}
}