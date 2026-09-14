class Solution {
    public String findContestMatch(int n) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(String.valueOf(i));
        }

        while (list.size() > 1) {
            List<String> temp = new ArrayList<>();
            for (int i = 0, j = list.size() - 1; i < j; i++, j--) {
                StringBuilder sb = new StringBuilder();
                sb.append("(").append(list.get(i)).append(",").append(list.get(j)).append(")");
                temp.add(sb.toString());
            }
            list = temp;
        }
        return list.get(0);
    }
}