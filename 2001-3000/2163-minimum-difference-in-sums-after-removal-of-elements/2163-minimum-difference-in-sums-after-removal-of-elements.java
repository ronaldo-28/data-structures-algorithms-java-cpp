class Solution {

    private static int MAX_VAL = 100_000;

    public long minimumDifference(int[] nums) {
        // minimize sum1, maximize sum2
        var firstHalfCounts = new int[MAX_VAL + 1];
        var secondHalfCounts = new int[MAX_VAL + 1];
        var n = nums.length / 3;
        // println("n = $n")
        for (int i = 0; i < n * 2; i++) {
            firstHalfCounts[nums[i]]++;
        }
        var sum1 = 0L;
        var sum1MinValue = 0;
        var totalCount = 0;
        while (true) {
            var count = firstHalfCounts[sum1MinValue];
            totalCount += count;
            // if (count > 0) {
            //     println("Add to sum1 $count * $sum1MinValue")
            // }
            sum1 += count * (long) sum1MinValue;
            if (totalCount >= n) {
                // println("Reached $sum1, havet to remove")
                var remain = totalCount - n;
                // totalCount -= (totalCount - n) * count
                sum1 -= remain * (long) sum1MinValue;
                firstHalfCounts[sum1MinValue] = remain;
                break;
            }
            firstHalfCounts[sum1MinValue++] = 0;
        }
        var sum2 = 0L;
        for (int i = n * 2; i < nums.length; i++) {
            var num = nums[i];
            sum2 += num;
            secondHalfCounts[num]++;
        }
        var sum2MinVal = 1;
        while (secondHalfCounts[sum2MinVal] == 0) {
            sum2MinVal++;
        }
        var minDiff = sum1 - sum2;
        // println("First: ${nums.asList().subList(0, n * 2).sorted()}, second: ${nums.asList().subList(n * 2, n * 3).sorted()}")
        // println("$sum1 - $sum2 = $minDiff")
        // run {
        //     val i = n * 2
        //     println("Real sum1: ${nums.asList().subList(0, i).sorted().subList(0, n).sum()}")
        //     println("Real sum2: ${nums.asList().subList(i, n * 3).sorted().subList(n * 2 - i, n * 3 - i).sum()}")
        // }
        for (int i = n * 2 - 1; i >= n; i--) {
            var num = nums[i];
            // remove num from first half
            var count1 = firstHalfCounts[num];
            if (count1 > 0) {
                firstHalfCounts[num] = count1 - 1;
            } else {
                // println("Current min: $sum1MinValue")
                sum1 -= num;
                while (firstHalfCounts[sum1MinValue] == 0) {
                    sum1MinValue++;
                }
                firstHalfCounts[sum1MinValue]--;
                sum1 += sum1MinValue;
                // println("In sum1, replace $num with $sum1MinValue")
            }
            if (num > sum2MinVal) {
                secondHalfCounts[num]++;
                secondHalfCounts[sum2MinVal]--;
                sum2 += (num - sum2MinVal);
                while (secondHalfCounts[sum2MinVal] == 0) {
                    sum2MinVal++;
                }
            }
            // println("First: ${nums.asList().subList(0, i).sorted()}, second: ${nums.asList().subList(i, n * 3).sorted()}")
            // println("i = $i: $sum1 - $sum2 = ${sum1 - sum2}")
            // println("Real sum1: ${nums.asList().subList(0, i).sorted().subList(0, n).sum()}")
            // println("Real sum2: ${nums.asList().subList(i, n * 3).sorted().subList(n * 2 - i, n * 3 - i).sum()}")
            minDiff = Math.min(minDiff, sum1 - sum2);
        }

        return minDiff;
    }
}