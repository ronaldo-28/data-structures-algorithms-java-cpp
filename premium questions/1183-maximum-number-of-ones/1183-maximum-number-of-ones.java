class Solution {
    public int maximumNumberOfOnes(int width, int height, int sideLength, int maxOnes) {
        int[] candidate = new int[sideLength * sideLength];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                candidate[(i % sideLength) * sideLength + (j % sideLength)]++;
            }
        }
        int ans = 0;
        Arrays.sort(candidate);
        for (int i = candidate.length - 1, j = maxOnes; j > 0; i--, j--) {
            ans += candidate[i];
        }
        return ans;
    }
}