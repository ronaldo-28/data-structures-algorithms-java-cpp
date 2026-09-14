class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] price = new int[n];
        Arrays.fill(price, Integer.MAX_VALUE);
        price[src] = 0;
        for (int i = 0; i <= k; i++) {
            if (!findPath(flights, price)) {
                break;
            }
        }
        return price[dst] == Integer.MAX_VALUE ? -1 : price[dst];
    }
    private boolean findPath(int[][] flights, int[] price) {
        int[] temp = Arrays.copyOf(price, price.length);
        boolean isFound = false;
        for (int[] f : flights) {
            if (temp[f[0]] != Integer.MAX_VALUE && price[f[1]] > f[2] + temp[f[0]]) {
                price[f[1]] = f[2] + temp[f[0]];
                isFound = true;
            }
        }
        return isFound;
    }
}