class Solution {
    public List<Integer> beautifulIndices(String s, String a, String b, int k) {
        List<Integer> ans = new ArrayList<>();
        int[] lcpA = findLCP(a.toCharArray()), lcpB = findLCP(b.toCharArray());
        int isa = 0, isb = 0, ia = 0, ib = 0, ls = s.length(), la = a.length(), lb = b.length();
        int lastA = -1000000000, lastB = -1000000000;
        char[] arrs = s.toCharArray(), arra = a.toCharArray(), arrb = b.toCharArray();
        while (isa < ls) {
            if (arrs[isa] == arra[ia]) {
                isa++;
                ia++;
            }
            if (ia == la) {
                lastA = isa - ia;
                //System.out.println(lastA);
                //A match A is found, we start to find B from the last match
                //Embedded KMP search
                while (isb < ls) {
                    //Condition is met or B is out of right range
                    if (Math.abs(lastA - lastB) <= k || lastB - lastA > k)
                        break;
                    if (arrs[isb] == arrb[ib]) {
                        isb++;
                        ib++;
                    }
                    if (ib == lb) {
                        lastB = isb - ib;
                        //System.out.println(lastB);
                        ib = lcpB[ib - 1];
                    } else if (isb < ls && arrs[isb] != arrb[ib]) {
                        if (ib == 0)
                            isb++;
                        else
                            ib = lcpB[ib - 1];
                    }
                }
                //Contition is met
                if (Math.abs(lastA - lastB) <= k && (ans.size() == 0 || lastA != ans.get(ans.size() - 1)))
                    ans.add(lastA);
                ia = lcpA[ia - 1];
            } else if (isa < ls && arrs[isa] != arra[ia]) {
                if (ia == 0)
                    isa++;
                else
                    ia = lcpA[ia - 1];
            }
        }
        return ans;
    }

    //Our old friend, KMP pattern
    private int[] findLCP(char[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        int i = 1, j = 0;
        while (i < n) {
            if (arr[i] == arr[j])
                res[i++] = ++j;
            else if (j == 0)
                i++;
            else
                j = res[j - 1];
        }
        return res;
    }
}