class Solution {
    public int maxRemoval(int[] nums, int[][] queries) {
        int n=nums.length;
        Arrays.sort(queries,(a,b)->a[0]-b[0]);
        PriorityQueue<Integer>pq=new PriorityQueue<>(
            (a,b)->b-a
        );
        int[] diff=new int[n+1];
        int active=0;
        int used=0;
        int q=0;
        for(int i=0;i<n;i++){
            active+=diff[i];
            while(q<queries.length&&queries[q][0]<=i){
                pq.add(queries[q][1]);
                q++;
            }while(active<nums[i]){
                if(pq.isEmpty()){
                    return -1;
                }int r=pq.poll();
                if(r<i){
                    return -1;
                }active++;
                used++;
                diff[r+1]--;
            }
        }return queries.length-used;
    }
}