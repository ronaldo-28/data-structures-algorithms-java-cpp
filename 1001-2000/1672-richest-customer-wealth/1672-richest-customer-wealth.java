class Solution {
    public int maximumWealth(int[][] accounts) {
        // Step 1: Initialize a variable to keep track of the maximum wealth found.
        int maxWealth = 0;
        
        // Step 2: Iterate through each customer in the accounts array.
        for (int i = 0; i < accounts.length; i++) {
            int currentWealth = 0;
            // Step 3: Sum the wealth from all banks for the current customer.
            for (int j = 0; j < accounts[i].length; j++) {
                currentWealth += accounts[i][j];
            }
            // Step 4: Update maxWealth if the current customer's wealth is greater.
            if (currentWealth > maxWealth) {
                maxWealth = currentWealth;
            }
        }
        
        // Step 5: Return the maximum wealth found.
        return maxWealth;
    }
}
