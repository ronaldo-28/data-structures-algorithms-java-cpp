class Solution {
    private static final int ALPHABET_SIZE = 10; 
    public int score(String[] cards, char x) {
        int[] frequency = new int[ALPHABET_SIZE * ALPHABET_SIZE];
        for (String card : cards) {
            int first = card.charAt(0) - 'a';
            int second = card.charAt(1) - 'a';
            frequency[first * ALPHABET_SIZE + second]++;
        }
        int xIndex = x - 'a';
        int xRow = xIndex * ALPHABET_SIZE;
        int doubleCount = frequency[xRow + xIndex];
        int firstTotal = 0;
        int secondTotal = 0;
        int firstMax = 0;
        int secondMax = 0;
        for (int letter = 0; letter < ALPHABET_SIZE; letter++) {
            if (letter == xIndex) {
                continue;
            }
            int firstCount = frequency[xRow + letter];
            int secondCount = frequency[letter * ALPHABET_SIZE + xIndex];
            firstTotal += firstCount;
            secondTotal += secondCount;
            firstMax = Math.max(firstMax, firstCount);
            secondMax = Math.max(secondMax, secondCount);
        }
        int basePairs = Math.min(firstTotal / 2, firstTotal - firstMax)
                + Math.min(secondTotal / 2, secondTotal - secondMax);
        int otherCount = firstTotal + secondTotal;
        return Math.min(otherCount,
                Math.min(basePairs + doubleCount, (otherCount + doubleCount) / 2));
    }
}