class Solution {
    public boolean phonePrefix(String[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                if (i == j) {
                    continue;
                }
                if (numbers[j].startsWith(numbers[i])) {
                    return false;
                }
            }
        }
        return true;
    }
}