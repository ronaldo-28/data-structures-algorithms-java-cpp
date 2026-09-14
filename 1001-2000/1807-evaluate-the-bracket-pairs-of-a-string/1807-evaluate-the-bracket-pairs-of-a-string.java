// class Solution {
//     public String evaluate(String s, List<List<String>> knowledge) {
        
//     }
// }
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        // Pre-calculate hash map capacity to reduce resizing overhead
        Map<String, String> knowledgeMap = new HashMap<>(knowledge.size() * 4 / 3 + 1);
        for (List<String> entry : knowledge) {
            knowledgeMap.put(entry.get(0), entry.get(1));
        }

        StringBuilder resultBuilder = new StringBuilder();
        char[] inputChars = s.toCharArray();
        int inputLength = inputChars.length;
        
        for (int i = 0; i < inputLength; i++) {
            if (inputChars[i] == '(') {
                int keyStart = ++i;
                // Scan for the closing bracket
                while (inputChars[i] != ')') {
                    i++;
                }
                
                // Extract key and append value from map
                String bracketKey = new String(inputChars, keyStart, i - keyStart);
                String replacementValue = knowledgeMap.get(bracketKey);
                
                if (replacementValue != null) {
                    resultBuilder.append(replacementValue);
                } else {
                    resultBuilder.append('?');
                }
            } else {
                resultBuilder.append(inputChars[i]);
            }
        }

        return resultBuilder.toString();
    }
}