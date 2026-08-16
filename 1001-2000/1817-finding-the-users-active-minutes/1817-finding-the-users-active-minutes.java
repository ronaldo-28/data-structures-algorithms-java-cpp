// class Solution {
//     public int[] findingUsersActiveMinutes(int[][] logs, int k) {
        
//     }
// }
import java.util.Arrays;

class Solution {
    public int[] findingUsersActiveMinutes(int[][] logs, int k) {
        int logsLength = logs.length;
        long[] packedLogs = new long[logsLength];

        for (int i = 0; i < logsLength; i++) {
            packedLogs[i] = ((long) logs[i][0] << 32) | logs[i][1];
        }

        Arrays.sort(packedLogs);

        int[] activeMinutesDistribution = new int[k];
        int currentIndex = 0;

        while (currentIndex < logsLength) {
            int currentUserId = (int) (packedLogs[currentIndex] >> 32);
            int uniqueMinutesCount = 0;

            while (currentIndex < logsLength && (int) (packedLogs[currentIndex] >> 32) == currentUserId) {
                uniqueMinutesCount++;
                int lastMinute = (int) packedLogs[currentIndex];
                
                while (currentIndex < logsLength && 
                       (int) (packedLogs[currentIndex] >> 32) == currentUserId && 
                       (int) packedLogs[currentIndex] == lastMinute) {
                    currentIndex++;
                }
            }

            if (uniqueMinutesCount <= k) {
                activeMinutesDistribution[uniqueMinutesCount - 1]++;
            }
        }

        return activeMinutesDistribution;
    }
}