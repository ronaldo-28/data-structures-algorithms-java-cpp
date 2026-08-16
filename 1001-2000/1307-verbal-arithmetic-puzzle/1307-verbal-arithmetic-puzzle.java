class Solution {
    public boolean isSolvable(String[] words, String result) {
        int[][] info = new int[26][4];
        for (String word : words) {
            int base = 1;
            for (int j = word.length() - 1; j >= 0; j--) {
                info[word.charAt(j) - 'A'][0] += base;
                base *= 10;
            }
            if (word.length() > 1)
                info[word.charAt(0) - 'A'][1] = 1;
        }
        int base = 1;
        for (int i = result.length() - 1; i >= 0; i--) {
            info[result.charAt(i) - 'A'][0] -= base;
            base *= 10;
        }
        if (result.length() > 1)
            info[result.charAt(0) - 'A'][1] = 1;
        int[][] choose = new int[10][4];
        int size = 0;
        for (int[] cur : info) {
            if (cur[0] != 0 || cur[1] != 0)
                choose[size++] = cur;
        }
        choose = Arrays.copyOf(choose, size);
        Arrays.sort(choose, (a, b) -> Math.abs(b[0]) - Math.abs(a[0]));
        int max = 0, min = 0;
        for (int i = size - 1; i >= 0; i--) {
            choose[i][2] = max;
            choose[i][3] = min;
            int x = choose[i][0];
            if (x > 0)
                max += 9 * x;
            else
                min += 9 * x;
        }
        return backtrack(choose, 0, new boolean[10], 0);
    }
    boolean backtrack(int[][] choose, int index, boolean[] visited, int sum) {
        if (index == choose.length)
            return sum == 0;
        int x = choose[index][0];
        int max = choose[index][2];
        int min = choose[index][3];
        for (int i = 0; i < visited.length; i++) {
            if (visited[i])
                continue;
            if (choose[index][1] == 1 && i == 0)
                continue;
            if (i * x + sum + max < 0 || i * x + sum + min > 0)
                continue;
            visited[i] = true;
            if (backtrack(choose, index + 1, visited, sum + i * x))
                return true;
            visited[i] = false;
        }
        return false;
    }
}