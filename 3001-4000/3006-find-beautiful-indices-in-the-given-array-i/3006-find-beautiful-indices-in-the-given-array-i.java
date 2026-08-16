import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

class Solution {
    public List<Integer> beautifulIndices(String s, String a, String b, int k) {
        List<Integer> result = new ArrayList<Integer>();

        if (a.length() > s.length() || b.length() > s.length()) {
            return new ArrayList<Integer>();
        }

        if (s.indexOf(a) == -1 || s.indexOf(b) == -1) {
            return new ArrayList<Integer>();
        }

        int lastBindex = -1;
        for (int i = 0; i <= s.length() - a.length(); ) {
            int index = s.indexOf(a, i);
            if (index == -1) {
                break;
            } else {
                if (lastBindex != -1 && Math.abs(index - lastBindex) <= k) {
                    result.add(index);
                    i = index + 1;
                    continue;
                }

                i = index + 1;
                int start = Math.max(0, index - k);
                int end = Math.min(index + k, s.length());
                int bindex = s.indexOf(b, start);
                if (bindex > -1 && bindex <= end) {
                    result.add(index);
                    lastBindex = start + bindex;
                }
            }
        }

        return result;

    }
}