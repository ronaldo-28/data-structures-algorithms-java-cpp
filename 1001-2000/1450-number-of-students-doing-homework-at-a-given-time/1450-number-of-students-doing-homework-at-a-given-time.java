class Solution {
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        // Initialize a counter to keep track of the number of students doing homework at queryTime
        int doingHomework = 0;
        
        // Iterate through each student's homework session
        for (int i = 0; i < startTime.length; i++) {
            // Check if queryTime falls within the interval [startTime[i], endTime[i]]
            if (queryTime >= startTime[i] && queryTime <= endTime[i]) {
                // Increment the counter if the student was doing homework at queryTime
                doingHomework++;
            }
        }
        
        // Return the final count of students doing homework at queryTime
        return doingHomework;
    }
}