class Solution {
    public long maximumBeauty(int[] flowers, long newFlowers, int target, int full, int partial) {
        // first, bucket-sort flowers to know number of gardens with given number of flowers planted
        // we can improve the score by either adding enough flowers to elevate from the greatest partial to target (gaining full points),
        //    or adding flowers to improve the minimum score (gaining partial points)

        // how to determine maximum:
            // first, use flowers to fill gardens to target where possible, add remaining flowers to minimum gardens, calculate score
            // repeatedly remove flowers from most-increased filled gardens and distribute them to min partial gardens, and recalculate
            // be sure to consider leaving at least one almost-filled garden with the rest being full
        
        var numGardensWithCount = new int[target + 1];
        var totalFlowersNeeded = 0L;
        for (int f : flowers) {
            var fCount = Math.min(f, target);
            totalFlowersNeeded += target - fCount;
            numGardensWithCount[fCount]++;
        }
        if (numGardensWithCount[target] == flowers.length) {
            return flowers.length * (long) full;
        }
        // println("$totalFlowersNeeded, $newFlowers")
        if (totalFlowersNeeded <= newFlowers + 1) {
            var allButOne = full * (long) (flowers.length - 1) + partial * (long) (target - 1);
            if (totalFlowersNeeded == newFlowers + 1) {
                return allButOne;
            } else {
                return Math.max(
                    allButOne,
                    full * (long) flowers.length
                );
            }
        }
        var moved = 0;
        var maxUnderTarget = target - 1;
        while (numGardensWithCount[maxUnderTarget] == 0) {
            maxUnderTarget--;
        }
        var flowersRemainingForRaise = newFlowers;
        while (maxUnderTarget + flowersRemainingForRaise >= target) {
            flowers[moved++] = maxUnderTarget;
            flowersRemainingForRaise -= (target - maxUnderTarget);
            if (--numGardensWithCount[maxUnderTarget] == 0) {
                do {
                    maxUnderTarget--;
                } while (numGardensWithCount[maxUnderTarget] == 0);
            }
        }

        var flowersRemaining = (int) flowersRemainingForRaise;

        var minGarden = 1;
        while (numGardensWithCount[minGarden] == 0) {
            minGarden++;
        }
        while (true) {
            // println("$flowersRemaining for ${numGardensWithCount.asList()}")
            var minCount = numGardensWithCount[minGarden];
            if (minCount > flowersRemaining) {
                break;
            }
            flowersRemaining -= minCount;
            numGardensWithCount[++minGarden] += minCount;
        }
        var score = (moved + (long) numGardensWithCount[target]) * full + minGarden * (long) partial;
        var maxScore = score;

        while (moved > 0) {
            var lastMoved = flowers[--moved];
            score -= full;
            flowersRemaining += (target - lastMoved);
            if (lastMoved < minGarden) {
                numGardensWithCount[minGarden]++;
                flowersRemaining -= (minGarden - lastMoved);
            } else {
                numGardensWithCount[lastMoved]++;
            }
            var priorMinGarden = minGarden;
            while (true) {
                var minCount = numGardensWithCount[minGarden];
                if (minCount > flowersRemaining) {
                    break;
                }
                flowersRemaining -= minCount;
                numGardensWithCount[++minGarden] += minCount;
            }
            score += (minGarden - priorMinGarden) * partial;
            maxScore = Math.max(score, maxScore);
        }

        return maxScore;
    }
}