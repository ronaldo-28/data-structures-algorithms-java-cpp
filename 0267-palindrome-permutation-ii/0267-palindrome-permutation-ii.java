class Solution {
    List<String> ans = new ArrayList<>();
    public List<String> generatePalindromes(String s) {
        if (s.length() == 1) {
            return List.of(s);
        }
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        char oddChar = '#';
        int m = 0;
        for (char c = 'a'; c <= 'z'; c++) {
            if (count[c - 'a'] > 0) {
            if (count[c - 'a'] % 2 != 0) {
                if (oddChar != '#') {
                    return ans;
                }
                oddChar = c;
                }
                m += count[c - 'a'] >> 1;
            }
        }

        char[] arr = new char[m];
        int index = 0;
        for (char c = 'a'; c <= 'z'; c++) {
            int tmp = count[c - 'a'] >> 1;
            while (tmp-- > 0) {
                arr[index++] = c;
            }
        }
        helper(arr, 0, oddChar);
        return ans;
    }

    private void helper(char[] arr, int index, char oddChar) {
        if (index == arr.length) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < arr.length; i++) {
                sb.append(arr[i]);
            }
            if (oddChar != '#') {
                sb.append(oddChar);
            }
            for (int i = arr.length - 1; i >= 0; i--) {
                sb.append(arr[i]);
            }
            ans.add(sb.toString());
            return;
        }
        boolean[] visited = new boolean[26];
        for (int i = index; i < arr.length; i++) {
            if (visited[arr[i] - 'a']) {
                continue;
            }
            visited[arr[i] - 'a'] = true;
            swap(arr, i, index);
            helper(arr, index + 1, oddChar);
            swap(arr, i, index);
        }
    }

    private void swap(char[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}