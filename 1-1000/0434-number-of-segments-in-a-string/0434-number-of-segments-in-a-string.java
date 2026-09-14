class Solution {
    public int countSegments(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int segmentCount = 0;
        boolean inSegment = false;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                if (!inSegment) {
                    segmentCount++;
                    inSegment = true;
                }
            } else {
                inSegment = false;
            }
        }

        return segmentCount;
    }
}