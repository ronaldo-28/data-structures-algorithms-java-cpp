class Solution {
    public int[][] indexPairs(String text, String[] words) {
        
        List<int[]> pairs = new ArrayList<>();

        for (var word: words) {
            for(int i = text.indexOf(word); i != -1;) {
                pairs.add(new int[] {i, (i + word.length() - 1)});
                i = text.indexOf(word, i + 1);
            }
        }

        pairs.sort((x,y) -> 
                x[0] - y[0] == 0
                    ? x[1] - y[1]
                    : x[0] - y[0]);
        
        return pairs.toArray(new int [0][0]);
    }
 
}