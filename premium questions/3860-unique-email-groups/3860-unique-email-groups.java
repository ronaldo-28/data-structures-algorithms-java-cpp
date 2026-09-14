class Solution {
    public int uniqueEmailGroups(String[] emails) {
        return sol1(emails);
    }


    private int sol1(String[] emails) {
        Set<String> set = new HashSet<>();
        for (String email : emails) {
            int index = email.indexOf('@');
            String lname = normLocal(email.substring(0, index));
            String dname = normDomain(email.substring(index + 1));
            String fname = new StringBuilder().append(lname).append('@').append(dname).toString();
            set.add(fname);
        }
        return set.size();
    }


    private String normLocal(String str) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '.') {
                continue;
            }
            if (ch == '+') {
                break;
            }
            builder.append(Character.toLowerCase(ch));
        }
        return builder.toString();
    }


    private String normDomain(String str) {
        return str.toLowerCase();
    }
}