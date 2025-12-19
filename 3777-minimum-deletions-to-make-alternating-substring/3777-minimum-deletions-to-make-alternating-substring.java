class Solution {
    public int[] minDeletions(String s, int[][] queries) {
        int count = 0;
        for (int[] query : queries) {
            count += query[0] - 1;
        }
        if (s.length() == 1)
            return new int[count];
        char[] chrs = s.toCharArray();
        BIT = new int[chrs.length + 1];
        boolean[] arr = new boolean[chrs.length];
        for (int i = 1; i < chrs.length; i++) {
            if (chrs[i] == chrs[i - 1]) {
                insert(i, 1);
                arr[i] = true;
            }
        }
        int[] res = new int[count];
        int idx = 0;
        for (int[] query : queries) {
            if (query[0] == 1) {
                if (query[1] < arr.length - 1) {
                    if (arr[query[1] + 1]) {
                        arr[query[1] + 1] = false;
                        insert(query[1] + 1, -1);
                    } else {
                        arr[query[1] + 1] = true;
                        insert(query[1] + 1, 1);
                    }
                }
                if (arr[query[1]]) {
                    arr[query[1]] = false;
                    insert(query[1], -1);
                } else {
                    arr[query[1]] = true;
                    insert(query[1], 1);
                }
            } else {
                res[idx++] = get(query[2]) - get(query[1]);
            }
        }
        return res;
    }

    int[] BIT;

    public void insert(int i, int num) {
        i++;
        while (i < BIT.length) {
            BIT[i] += num;
            i += i & -i;
        }
    }

    public int get(int i) {
        i++;
        int res = 0;
        while (i > 0) {
            res += BIT[i];
            i -= i & -i;
        }
        return res;
    }
}