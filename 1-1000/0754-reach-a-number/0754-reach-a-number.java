class Solution {
    public int reachNumber(int target) {
        //x * (x + 1) / 2 == target
        //x^2 + x == 2 * target
        //x^2 + x + 0.25 = 2 * target + 0.25
        //(x + 0.5)^2 = 2 * target + 0.25
        //x + 0.5 = sqrt(2 * target + 0.25)
        //x = sqrt(2 * target + 0.25) - 0.5;
        int steps = (int)Math.ceil(Math.sqrt(2 * Math.abs(target) + 0.25) - 0.5);
        int dist = steps * (steps + 1) / 2 - target;
        return steps + (dist % 2) * (steps % 2 + 1);
    }
}