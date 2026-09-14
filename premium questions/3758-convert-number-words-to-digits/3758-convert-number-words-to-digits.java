class Solution {
    private static Trie trie = new Trie();
    
    public String convertNumber(String s) {

        return trie.getNumber(s);
    }
}

public class Trie {
    private static class TrieNode {
        private TrieNode[] children = new TrieNode[26];
        private String digit = null;
    }

    private static Map<String, String> numbers = new HashMap<>(10);

    static {
        numbers.put("zero", "0");
        numbers.put("one", "1");
        numbers.put("two", "2");
        numbers.put("three", "3");
        numbers.put("four", "4");
        numbers.put("five", "5");
        numbers.put("six", "6");
        numbers.put("seven", "7");
        numbers.put("eight", "8");
        numbers.put("nine", "9");
    }

    public Trie() {
        insert("zero");
        insert("one");
        insert("two");
        insert("three");
        insert("four");
        insert("five");
        insert("six");
        insert("seven");
        insert("eight");
        insert("nine");
    }

    private TrieNode root = new TrieNode();

    public String getNumber(String number) {
        TrieNode trieNode;
        int index;
        char ch;
        int startIndexChar = 0;
        int currentIndexChar;
        StringBuilder result = new StringBuilder();

        while (startIndexChar < number.length()) {

            currentIndexChar = startIndexChar;
            trieNode = root;

            while (currentIndexChar < number.length()) {

                ch = number.charAt(currentIndexChar);
                index = ch - 'a';

                if (trieNode.children[index] == null) {

                    break;

                } else if (trieNode.children[index].digit != null) {

                    result.append(trieNode.children[index].digit);
                    startIndexChar = currentIndexChar;

                    break;

                } else {

                    trieNode = trieNode.children[index];
                    currentIndexChar++;

                }
            }

            startIndexChar++;

        }

        return result.toString();
    }

    private void insert(String digit) {
        TrieNode trieNode = root;
        int index;

        for (char ch : digit.toCharArray()) {
            index = ch - 'a';

            if (trieNode.children[index] == null) {
                trieNode.children[index] = new TrieNode();
            }

            trieNode = trieNode.children[index];
        }

        trieNode.digit = numbers.get(digit);
    }
}