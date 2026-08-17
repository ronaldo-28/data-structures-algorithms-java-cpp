class Solution {
    public List<String> generatePossibleNextMoves(String currentState) {
        Set<String> set = new HashSet();

        if(currentState.length() < 2) {
            return new ArrayList(set);
        }
        StringBuilder sb = new StringBuilder(currentState);

        for(int i = 0; i < currentState.length()-1; i++) {
            if(currentState.charAt(i) == '+' && currentState.charAt(i+1) == '+') {
                sb.setCharAt(i, '-');
                sb.setCharAt(i+1, '-');
                set.add(sb.toString());
                sb.setCharAt(i, '+');
                sb.setCharAt(i+1, '+');
            }
        }
        return new ArrayList(set);
    }
}