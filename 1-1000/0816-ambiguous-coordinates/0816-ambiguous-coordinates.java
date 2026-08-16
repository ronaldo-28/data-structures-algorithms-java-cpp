class Solution {
    public List<String> ambiguousCoordinates(String s) {
        List<String> allPoss = new ArrayList();
        for(int x = 1; x < s.length() - 2; x++) {
            addAll(allPoss, possDec(s, 1, x), possDec(s, x + 1, s.length() - 2));
        }
        return allPoss;
    }

    public void addAll(List<String> all, List<String> lefts, List<String> rights) {
        for(String left : lefts) {
            for(String right : rights) {
                StringBuilder sb = new StringBuilder();
                sb.append('(');
                sb.append(left);
                sb.append(", ");
                sb.append(right);
                sb.append(')');
                all.add(sb.toString());
            }
        }
    }

    public List<String> possDec(String num, int s, int e) {
        List<String> poss = new ArrayList();
        if(s == e) {
            poss.add(num.substring(s, e + 1));
            return poss;
        }
        boolean hasNonZero = false;
        for(int x = s; x <= e; x++) {
            if(num.charAt(x) != '0') {
                hasNonZero = true;
            }
        }
        if(!hasNonZero) {
            return poss;
        }
        if(num.charAt(s) == '0' && num.charAt(e) == '0') {
            return poss;
        }
        if(num.charAt(s) == '0') {
            StringBuilder sb = new StringBuilder();
            sb.append("0.");
            sb.append(num.substring(s + 1, e + 1));
            poss.add(sb.toString());
            return poss;
        }
        poss.add(num.substring(s, e + 1));
        if(num.charAt(e) == '0') {
            return poss;
        }
        for(int y = s + 1; y <= e; y++) {
            StringBuilder sb = new StringBuilder();
            sb.append(num.substring(s, y));
            sb.append('.');
            sb.append(num.substring(y, e + 1));
            poss.add(sb.toString());
        }
        return poss;
    }
}