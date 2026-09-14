class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        var n = intervals.length;

        if(n <= 1)
            return true;

        sort(intervals, 0, n-1);
        
        for(int i=1; i < n; i++) {
            var prev = intervals[i-1];
            var curr = intervals[i];

            if(prev[1] > curr[0]) {
                return false;
            }
        }

        return true;
    }
 
    void sort(int[][] arr, int l, int r) {
        var pivot = arr[l + (r - l) / 2];

        var i = l;
        var j = r;

        while(i <= j) {
            while(predicate(pivot, arr[i])) i++;
            while(neg_predicate(pivot, arr[j])) j--;

            if(i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }

        if (i < r)
            sort(arr, i, r);
        
        if (j > l)
            sort(arr, l, j);
    }

    boolean predicate(int[] arr, int[] pivot) {
        if(arr[0] == pivot[0])
            return arr[1] > pivot[1];
        
        return arr[0] > pivot[0];
    }


    boolean neg_predicate(int[] arr, int[] pivot) {
        if(arr[0] == pivot[0])
            return arr[1] < pivot[1];
        
        return arr[0] < pivot[0];
    }

    void swap(int[][] arr, int l, int r) {
        var temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }
}