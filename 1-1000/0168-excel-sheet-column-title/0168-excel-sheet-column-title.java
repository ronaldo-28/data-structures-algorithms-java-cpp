class Solution {
    public String convertToTitle(int columnNumber) {
        StringBuilder result = new StringBuilder();
        while (columnNumber > 0) {
            columnNumber--; // decrement columnNumber to make it 0-indexed
            char ch = (char) (columnNumber % 26 + 'A');
            result.insert(0, ch);
            columnNumber /= 26;
        }
        return result.toString();
    }
}
