import java.util.*;

class Solution {

    ArrayList<ArrayList<Integer>> vec = new ArrayList<>();
    HashMap<Integer, Integer> mp = new HashMap<>();
    int[] arr = new int[501];

    // Finds minimum horizontal distance between two common x-coordinates
    int intersect(int i1, int i2) {
        int i = 0, j = 0;
        int ans = Integer.MAX_VALUE;
        Integer prev = null;

        ArrayList<Integer> a = vec.get(i1);
        ArrayList<Integer> b = vec.get(i2);

        while (i < a.size() && j < b.size()) {
            int x1 = a.get(i);
            int x2 = b.get(j);

            if (x1 == x2) {
                if (prev != null) {
                    ans = Math.min(ans, x1 - prev);
                }
                prev = x1;
                i++;
                j++;
            } else if (x1 < x2) {
                i++;
            } else {
                j++;
            }
        }

        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public int minAreaRect(int[][] points) {
        int index = 0;
        int sol = Integer.MAX_VALUE;

        Arrays.fill(arr, -1);

        // Group x-values by y-coordinate
        for (int[] p : points) {
            int x = p[0];
            int y = p[1];

            if (mp.containsKey(y)) {
                vec.get(mp.get(y)).add(x);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(x);
                vec.add(list);
                mp.put(y, index);
                arr[index] = y;
                index++;
            }
        }

        // Sort x-values for each horizontal line
        for (ArrayList<Integer> list : vec) {
            Collections.sort(list);
        }

        // Compare all pairs of horizontal lines
        for (int i = 0; i < vec.size() - 1; i++) {
            if (vec.get(i).size() < 2) continue;

            for (int j = i + 1; j < vec.size(); j++) {
                if (vec.get(j).size() < 2) continue;

                int width = intersect(i, j);
                if (width > 0) {
                    int height = Math.abs(arr[i] - arr[j]);
                    int area = width * height;
                    sol = Math.min(sol, area);
                }
            }
        }

        return sol == Integer.MAX_VALUE ? 0 : sol;
    }
}