class Solution {
    public int maxTransactions(int[] transactions) {
        int count = 0;
        long sum = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0;i<transactions.length;i++){
            if(sum+transactions[i]<0){
                if(!pq.isEmpty() && pq.peek()<transactions[i]){
                    int temp = pq.poll();
                    sum = sum+transactions[i]-temp;
                    pq.add(transactions[i]);
                }
            }else{
                sum+=transactions[i];
                count++;
                if(transactions[i]<0){
                    pq.add(transactions[i]);
                }
            }
        }
        return count;
    }
}