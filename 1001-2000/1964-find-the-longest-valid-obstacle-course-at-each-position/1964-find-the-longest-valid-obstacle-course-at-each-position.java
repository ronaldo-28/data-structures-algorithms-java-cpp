class Solution {
    int n, len=1;
    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        n = obstacles.length;
        int[] list = new int[n];
        int[] res = new int[n];
        res[0] = 1;
        list[0] = obstacles[0];
        for(int i=1; i<n; i++){
            res[i] = bsearch(list, obstacles[i]);
        }
        return res;
    }

    public int bsearch(int[] list, int num) {
        if(num >= list[len-1]) {
            // System.out.println("already higher");
            list[len] = num;
            return ++len;
        }
        int l=0, r=len-1, mid;
        int idx = r;
        while(l <= r){
            mid = (l+r)>>1;
            if(list[mid] > num){
                idx = mid;
                r = mid - 1;
            } else {
                l = mid+1;
            }
        }
        // System.out.println(list);
        // System.out.println("found upper idx: " + idx + " num: " + num);
        list[idx]=num;
        return idx+1;
    }
}