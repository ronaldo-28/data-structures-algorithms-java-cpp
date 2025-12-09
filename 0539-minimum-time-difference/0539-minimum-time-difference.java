class Solution {
    int parseTimeStr(final String s) {
        int res = 0;
        res += (s.charAt(0) - '0') * 10 * 60;
        res += (s.charAt(1) - '0') * 60;
        res += (s.charAt(3) - '0') * 10;
        res += (s.charAt(4) - '0');
        return res;
    }
    public int findMinDifference(List<String> timePoints) {
        if (timePoints.size() > 60 * 24) return 0;

        final boolean[] seen = new boolean[60 * 24];
        for (final String timeStr : timePoints) {
            final int time = parseTimeStr(timeStr);
            if (seen[time]) return 0;

            seen[time] = true;
        }

        int res = Integer.MAX_VALUE, first = 0;
        while (first < seen.length && !seen[first]) first++;
        
        int r = first, l = first;
        while (true) {
            l = r;

            do { 
                r++;
            }
            while (r < seen.length && !seen[r]);

            if (r == seen.length) break;

            final int diff = r - l;
            if (diff < res) res = diff;
        }
        
        // compare first and last
        {
            final int last = l;
            first += (60 * 24);

            final int diff = first - last;
            if (diff < res) res = diff;
        }

        return res;
    }
}