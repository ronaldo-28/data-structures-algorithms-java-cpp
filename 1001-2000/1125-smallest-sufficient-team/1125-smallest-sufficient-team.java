class Solution {
    
    List<Integer> ans = new ArrayList<>();
    
    public int[] smallestSufficientTeam(String[] skills, List<List<String>> people) {
        int n = skills.length, m = people.size();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(skills[i], i);
        }
        int[] arr = new int[m];
        for (int i = 0; i < m; i ++) {
            List<String> list = people.get(i);
            int val = 0;
            for (String skill : list) {
                val |= 1 << map.get(skill);
            }
            arr[i] = val;
        }
        boolean[] banned = new boolean[m];
        for (int i = 0; i < m; i ++) {
            for (int j = i + 1; j < m; j ++) {
                int val = arr[i] | arr[j];
                if (val == arr[i]) {
                    banned[j] = true;
                } else if (val == arr[j]) {
                    banned[i] = true;
                }
            }
        }
        // System.out.println(Arrays.toString(banned));
        // System.out.println(Arrays.toString(arr));
        helper(0, n, arr, new ArrayList<>(), banned);
        // System.out.println(ans);
        int[] res = new int[ans.size()];
        for (int i = 0; i < res.length; i ++) {
            res[i] = ans.get(i);
        }
        return res;
    }
    
    private void helper(int cur, int n, int[] arr, List<Integer> list, boolean[] banned) {
        if (cur == (1 << n) - 1) {
            if (ans.isEmpty() || ans.size() > list.size()) {
                ans = new ArrayList<>(list);
            }
            return;
        }
        if (!ans.isEmpty() && list.size() >= ans.size()) {
            return;
        }
        int zero = 0;
        while (((cur >> zero) & 1) == 1) {
            zero ++;
        }
        for (int i = 0; i < arr.length; i++) {
            if (banned[i]) {
                continue;
            }
            if (((arr[i] >> zero) & 1) == 1) {
            // if ((cur | arr[i]) > cur) {
                list.add(i);
                helper(cur | arr[i], n, arr, list, banned);
                list.remove(list.size() - 1);
            }
        }
    }
}