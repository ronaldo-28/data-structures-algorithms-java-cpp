// class Solution {
//     public double maxAverageRatio(int[][] classes, int extraStudents) {
        
//     }
// }
class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        int n = classes.length;
        double[] gains = new double[n + 1];
        int[] passCounts = new int[n + 1];
        int[] totalCounts = new int[n + 1];
        double currentTotalRatio = 0;

        for (int i = 0; i < n; i++) {
            int pass = classes[i][0];
            int total = classes[i][1];
            passCounts[i + 1] = pass;
            totalCounts[i + 1] = total;
            gains[i + 1] = (double) (pass + 1) / (total + 1) - (double) pass / total;
            currentTotalRatio += (double) pass / total;
        }

        // Build heap in O(N)
        for (int i = n / 2; i >= 1; i--) {
            sink(i, n, gains, passCounts, totalCounts);
        }

        while (extraStudents-- > 0) {
            // The top of the heap (index 1) has the maximum gain
            currentTotalRatio += gains[1];
            
            int pass = ++passCounts[1];
            int total = ++totalCounts[1];
            
            // Update gain for the next potential student
            gains[1] = (double) (pass + 1) / (total + 1) - (double) pass / total;
            
            // Push the updated class down to its correct position
            sink(1, n, gains, passCounts, totalCounts);
        }

        return currentTotalRatio / n;
    }

    private void sink(int index, int n, double[] gains, int[] passCounts, int[] totalCounts) {
        double targetGain = gains[index];
        int targetPass = passCounts[index];
        int targetTotal = totalCounts[index];
        
        int half = n >>> 1;
        while (index <= half) {
            int child = index << 1;
            if (child < n && gains[child] < gains[child + 1]) {
                child++;
            }
            if (targetGain >= gains[child]) break;
            
            // Move child up instead of swapping
            gains[index] = gains[child];
            passCounts[index] = passCounts[child];
            totalCounts[index] = totalCounts[child];
            index = child;
        }
        
        // Place the target values into the final hole
        gains[index] = targetGain;
        passCounts[index] = targetPass;
        totalCounts[index] = targetTotal;
    }
}