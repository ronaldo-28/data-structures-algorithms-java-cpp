class Solution {
    public int minMeetingRooms(int[][] intervals) {
        int len = intervals.length;
        if(len == 0) return 0;
        int[] start = new int[len], end = new int[len];
        for(int i = 0 ; i < len ; i++){
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }

        qSort(start, 0, start.length - 1);
        qSort(end, 0, end.length - 1);

        int count = 0, i = 0, j = 0;
        while(i < len){
            if(start[i] >= end[j]){
                j++;
            } else {
                count++;
            }
            i++;
        }
        return count;
    }

    private void qSort(int[] arr, int start, int end){
        if(start >= end) return;
        int pivot = arr[end];
        int i = start, j = end;
        while(i < j){
            while(i < j && arr[i] < pivot){
                i++;
            }
            while(i < j && arr[j] >= pivot){
                j--;
            }
            swap(arr, i, j);
        }
        swap(arr, i, end);
        qSort(arr, start, i - 1);
        qSort(arr, i + 1, end);
    }

    private void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}