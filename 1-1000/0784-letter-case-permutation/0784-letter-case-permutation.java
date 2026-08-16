class Solution {
    private static class LetterCased extends java.util.AbstractList<String> {
        private final char[] s;
        private final int[] letter_indices;
        private int current_permutation;

        LetterCased(char[] s, int[] letter_indices) {
            this.s = s;
            this.letter_indices = letter_indices;
            this.current_permutation = 0;
        }

        @Override
        public String get(int index) {
            index ^= index >> 1;
            int delta = current_permutation ^ index;
            if (delta != 0) {
                if ((delta & (delta-1)) == 0) {
                    s[letter_indices[Integer.numberOfTrailingZeros(delta)]] ^= 'a' ^ 'A';
                } else {
                    for (int i = 0; delta != 0 && i < letter_indices.length; ++i, delta >>= 1)
                        if ((delta & 1) != 0)
                            s[letter_indices[i]] ^= 'a' ^ 'A';
                }
            }
            current_permutation = index;
            return new String(s);
        }

        @Override
        public int size() {
            return 1 << letter_indices.length;
        }
    }

    public static List<String> letterCasePermutation(String s) {
        int letter_count = 0;
        char[] s_array = s.toCharArray();
        for (char c : s_array)
            if (('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z'))
                ++letter_count;
        int[] letter_indices = new int[letter_count];
        letter_count = 0;
        for (int i = 0; i < s_array.length; ++i) {
            char c = s_array[i];
            if (('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z'))
                letter_indices[letter_count++] = i;
        }

        return new LetterCased(s_array, letter_indices);
    }
}