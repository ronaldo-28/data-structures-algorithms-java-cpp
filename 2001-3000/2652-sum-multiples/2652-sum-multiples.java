class Solution {

    private int sum(int k, int n) {
        int count = n / k;
        return k * count * (count + 1) / 2;
    }

    public int sumOfMultiples(int n) {

        return sum(3, n)
             + sum(5, n)
             + sum(7, n)
             - sum(15, n)
             - sum(21, n)
             - sum(35, n)
             + sum(105, n);
    }
}