class Solution {
    public List<Long> maximumEvenSplit(long finalSum) {
        if((finalSum & 1) == 1) return new ArrayList<Long>();

        // Deque<Long> dq = new LinkedList<>();

        long nextItem = 2, total = 0;

        while(total < finalSum) {
            total += nextItem;
            nextItem += 2;

            // dq.offerLast(nextItem);
        }

        long remaining = total - finalSum;
        List<Long> result = new ArrayList<>();

        for(long i=2; i<nextItem; i+=2) {
            if(i == remaining) continue;

            result.add(i);
        }

        return result;
    }
}