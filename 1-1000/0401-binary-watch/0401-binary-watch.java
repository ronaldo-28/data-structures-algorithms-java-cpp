class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> result = new ArrayList<>();

        if (turnedOn <= 8) {
            calcTime(turnedOn, 0, 0, 0, result);
        }

        return result;
    }

    private void calcTime(int remaining, int ledPOS, int hour,  int min, List<String> result) {
        if (remaining == 0 && hour < 12 && min < 60) {
            StringBuilder sb = new StringBuilder();
            sb.append(hour).append(':');

            if (min < 10) {
                sb.append(0);
            }

            sb.append(min);

            result.add(sb.toString());
        } else {
            for (int start = ledPOS; start < 10; start++) {
                int prevHour = hour;
                int prevMin = min;

                if (start < 4) {
                    hour = hour + (int)Math.pow(2, start);
                    // System.out.println(remaining + " " + start + " h " + hour);
                } else {
                    min = min + (int)Math.pow(2, start-4);
                    // System.out.println(remaining + " " + start + " m " + min);
                }

                calcTime(remaining-1, start+1, hour, min, result);
                hour = prevHour;
                min = prevMin;
            }
        }
    }
}