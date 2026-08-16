/**
what are the representations?

10:00
or
9:60

1:10
or
70

9:59
or
8:119 < NO

leading 0s? 
never I think




 */

class Solution {
    public int minCostSetTime(int startAt, int moveCost, int pushCost, int targetSeconds) {
        int secs = targetSeconds % 60;
        int mins = targetSeconds / 60;

        return Math.min(
            calcTime(mins - 1, secs + 60, startAt, moveCost, pushCost),
            calcTime(mins, secs, startAt, moveCost, pushCost));
    }

    int calcTime(int mins, int seconds, int startAt, int moveCost, int pushCost) {
        if (mins > 99 || mins < 0) return  Integer.MAX_VALUE;
        if (seconds > 99) return Integer.MAX_VALUE;

        int[] nums = new int[4];

        nums[1] = mins % 10;
        mins /= 10;
        nums[0] = mins % 10;

        nums[3] = seconds % 10;
        seconds /= 10;
        nums[2] = seconds % 10;

        int totalCost = 0;

// ignore leading 0s
        int start = 0;
        while (start < 4 && nums[start] == 0) {
            start++;
        }

        for (int i = start; i < 4; i++) {
            int num = nums[i];
            if (startAt != num) {
                totalCost += moveCost;
            }
            totalCost += pushCost;
            startAt = num;
        }

        return totalCost;
    }
}