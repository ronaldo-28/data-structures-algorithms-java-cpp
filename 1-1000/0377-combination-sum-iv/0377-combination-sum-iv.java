class Solution {
    public int combinationSum4(int[] nums, int target) {
        return solveByDp(nums, target, new Integer[target + 1]);
    }

    int solveByDp(int[] nums, int target, Integer[] memo) {
        if (target < 0) {
            return 0;
        }

        if (target == 0) {
            return 1;
        }

        if (memo[target] != null) {
            return memo[target];
        }

        int result = 0;

        // result = solveByDp(nums, target, memo);
        // //int comboSum = 0;
        // result += (solveByDp(nums, target - nums[index], memo));

        for (int num : nums) {
            if (target - num >= 0) {
                result += solveByDp(nums, target - num, memo);
            }
        }

        return memo[target] = result;
    }

    int getCombinationCount(List<Integer> list) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : list) {
            int count = map.getOrDefault(num, 0);
            map.put(num, count + 1);
        }

        int result = getFactorial(list.size());

        for (int key : map.keySet()) {
            result = result / getFactorial(map.get(key));
        }

        return result;
    }

    int getFactorial(int num) {
        int result = 1;

        while (num > 0) {
            result = result * num;
            num--;
        }

        return result;
    }
}



// comb(index, target, memo)
//    (1)