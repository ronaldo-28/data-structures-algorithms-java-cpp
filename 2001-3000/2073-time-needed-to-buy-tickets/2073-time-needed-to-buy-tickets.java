class Solution {

    /**
     * Calculate the time required for a person to buy tickets.
     *
     * @param tickets Array representing the number of tickets each person in the queue needs.
     * @param k Index of the person whose time to buy tickets is being calculated.
     * @return The time required for the person at index k to buy their tickets.
     */
    public int timeRequiredToBuy(int[] tickets, int k) {
        // Initialize the answer variable to store the total time required.
        int totalTime = 0;

        // Loop through each person in the tickets array.
        for (int i = 0; i < tickets.length; i++) {
            // If the current person is before or at the position k in the queue,
            // they will buy tickets[i] amount or the same amount as the person
            // at the position k, whichever is smaller.
            if (i <= k) {
                totalTime += Math.min(tickets[k], tickets[i]);
            } else {
                // If the current person is after the position k in the queue,
                // they can only buy till tickets[k] - 1 as the person at k
                // will buy their last ticket before them.
                totalTime += Math.min(tickets[k] - 1, tickets[i]);
            }
        }

        // Return the total time required for the person at index k to buy their tickets.
        return totalTime;
    }
}