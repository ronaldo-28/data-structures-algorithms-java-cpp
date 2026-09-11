class Solution {
    public int minConnectedGroups(int[][] intervals, int k) {
        int index = 0, left = 0, n = intervals.length;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]); //sort by start value
        for(int i = 1; i < n; i++) {
            int start = intervals[i][0], end = intervals[i][1];
            if(start > intervals[index][1]) { //if the current interval cant be merged with the previous one
                intervals[++index] = intervals[i]; //copy the new interval to the correct index in the array
                if(intervals[left][1] < start - k) left++; //increment the position of the ealiest coverable interval if needed
            }else intervals[index][1] = Math.max(intervals[index][1], end); //otherwise merge the intervals
        }
        return left + 1; //answer is the count of intervals minus the max coverable amount -> index - (index - left) + 1 -> left + 1
    }
}