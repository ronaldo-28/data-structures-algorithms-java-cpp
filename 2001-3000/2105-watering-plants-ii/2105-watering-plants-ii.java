class Solution {
    public int minimumRefill(int[] plants, int capacityA, int capacityB) {
        int n = plants.length;
        int sum1 = 0;
        int count = 0;
        for(int i = 0; i < n / 2; i++) {
            sum1 += plants[i];
            if(sum1 > capacityA) {
                sum1 = plants[i];
                count++;
            }
        }
        int sum2 = 0;
        for(int i = n - 1; i > n / 2; i--) {
            sum2 += plants[i];
            if(sum2 > capacityB) {
                sum2 = plants[i];
                count++;
            }
        }
        if(n % 2 == 0 || capacityB - sum2 > capacityA - sum1) {
            if(sum2 + plants[n / 2] > capacityB) count++;
        }else if(sum1 + plants[n / 2] > capacityA) count++;
        return count;
    }
}