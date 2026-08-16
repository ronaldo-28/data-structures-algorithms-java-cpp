class Solution {
    public String makeLargestSpecial(String S) {
        if(S == null || S.length() == 0 || S.length() == 2) return S;
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> b.compareTo(a));
        int acc = 1, prev = 0;
        for(int i = 1; i <= S.length(); i++) {
            if(acc == 0) {
                if(!(prev == 0 && i == S.length())) pq.add(makeLargestSpecial(S.substring(prev, i)));
                prev = i;
            }
            if(i == S.length()) break;
            if(S.charAt(i) == '1') {
                acc++;
            }
            else {
                acc--;
            }
        }
        StringBuilder ans = new StringBuilder();
        while(!pq.isEmpty()) {
            ans.append(pq.poll());
        }
        if(ans.length() == 0) {
            ans.append('1');
            ans.append(makeLargestSpecial(S.substring(1, S.length() - 1)));
            ans.append('0');
        }
        return ans.toString();
    }
}