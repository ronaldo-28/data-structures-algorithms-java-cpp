class Solution {
    public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        PriorityQueue<state>pq=new PriorityQueue<>((a,b)->(b.val-a.val));
        int n=values.length;
        for(int i=0;i<n;i++){
            pq.add(new state(values[i],labels[i]));
        }
        int []arr=new int[(2*10000)+1];
        int count=1;
        int sum=0;
        while(!pq.isEmpty()){
            if(count>numWanted) break;
            state cur=pq.poll();
            if(arr[cur.label]+1>useLimit) continue;
            arr[cur.label]++;
            sum+=cur.val;
            count++;
        }
        return sum;
    }
}
class state{
    int val;
    int label;
    public state(int val,int label){
        this.val=val;
        this.label=label;
    }
}