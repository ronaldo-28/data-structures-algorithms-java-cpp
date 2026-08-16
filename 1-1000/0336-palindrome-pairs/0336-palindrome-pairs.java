class Solution {
    class Node {
        int index;
        int len;
        int revIndex;
        int revLen;

        Node[] children;

        Node () {
            this.children = new Node[26];
            this.index = -1;
            this.revIndex = -1;
        }
    }

    Node root = new Node();
    List<List<Integer>> pals = new ArrayList();

    public List<List<Integer>> palindromePairs(String[] words) {
        if (words.length == 0) return pals;

        Pair<String, Integer>[] p = new Pair[words.length];
        for (int i = 0; i < words.length; i++) p[i] = new Pair(words[i], i);
        // Add shorter strings first
        Arrays.sort(p, (a, b) -> a.getKey().length() - b.getKey().length());

        for (int i = 0; i < p.length; i++)
            findPal(p[i]);

        return pals;
    }

    // We add both original & reverse of each word -> prefix & suffix
    // versions of this word
    // When adding new words, first we check if it can prefix or suffix
    // to any existing word -> prefix checker checks for other prefixes
    // this word can suffix to, and suffix checker checks for other 
    // suffixes this word can prefix to

    // Pairs may be exactly reverse of each other (same length)
    // or one may be larger than the other
    // #1 - Same length: prefix & suffix will reach a word ending at the same
    //      time
    //      -> e.g. "abcd" exists while adding "dcba"
    // #2 - This word is longer & its pal will prefix to it: Suffix checker
    //      will reach a word ending first & then we need to check rest of
    //      this word if it is a palindrome
    //      -> e.g. "s" exists when adding "lls"
    //      -> e.g. "lls" exists when adding "sssll"
    //      -> e.g. "" exists when adding "aaa"
    //      -> e.g. "a" exists when adding "ba"
    // #3 - This word is longer & its pal will suffix to it: Prefix checker 
    //      will reach a word ending first & then we need to check rest of
    //      this word if it is a palindrome
    //      -> e.g. "" exists when adding "aaa"
    //      -> e.g. "a" exists when adding "ab"

    private void findPal(Pair<String, Integer> p) {
        String word = p.getKey();
        int index = p.getValue();

        Node prefix = root;
        Node suffix = root;

        int n = word.length();
        // We need to check the terminus nodes, so loop an extra time, but
        // break before adding any children
        for (int i = 0; i <= n; i++) {
            int j = n - 1 - i;

            // #1 & #2 - handle the case where the pal will prefix to this word
            //      -> e.g. "abcd" exists while adding "dcba"
            //      -> e.g. "s" exists when adding "lls"
            //      -> e.g. "lls" exists when adding "sssll"
            //      -> e.g. "" exists when adding "aaa"
            //      -> e.g. "a" exists when adding "ba"
            if (prefix.index != -1)
                // This word can suffix to something
                // We only need to check the portion longer than the word found by 
                // prefix, however, since prefix is matching in reverse, we need
                // to check this longer portion from the front
                // -> 0 to word.length() - prefix.word.length() - 1
                if (isPalindrome(word, 0, word.length() - prefix.len - 1))
                    pals.add(Arrays.asList(prefix.index, index));

            // #1 & #3 - handle the case where the pal will suffix to this word
            //      -> e.g. "abcd" exists while adding "dcba"
            //      -> e.g. "" exists when adding "aaa"
            //      -> e.g. "a" exists when adding "ab"
            if (suffix.revIndex != -1)
                // This word can prefix to something
                // We only need to check the portion longer than the word found by 
                // suffix
                // -> suffix.word.length() to word.length() - 1
                if (isPalindrome(word, suffix.revLen, word.length() - 1)) 
                    pals.add(Arrays.asList(index, suffix.revIndex));

            if (i == n) break;

            // Add this word in original as prefix - since suffix checker
            // is matching in original order, use suffix checker to add prefix
            int k = word.charAt(i) - 'a';
            if (suffix.children[k] == null) suffix.children[k] = new Node();
            suffix = suffix.children[k];

            // Add this word in reverse as suffix - since prefix checker
            // is matching in reverse order, use prefix checker to add suffix
            k = word.charAt(j) - 'a';
            if (prefix.children[k] == null) prefix.children[k] = new Node();
            prefix = prefix.children[k];
        }

        // Suffix checker is adding the original as prefix
        suffix.index = index;
        suffix.len = n;

        // Prefix checker is adding the original as suffix
        prefix.revIndex = index;
        prefix.revLen = n;
    }

    // Start & end inclusive
    private boolean isPalindrome(String s, int start, int end) {
        for (int i = start, j = end; i <= j; i++, j--) 
            if (s.charAt(i) != s.charAt(j)) return false;
        return true;
    }
}