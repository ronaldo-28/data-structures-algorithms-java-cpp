class Solution {
    public int[] makeParityAlternating(int[] nums) {
        if (nums.length == 1) return new int[]{0, 0};
        int evenCost = 0; //cost when 1st num is even
        int evenMin = Integer.MAX_VALUE;
        int evenMax = Integer.MIN_VALUE;
        int oddCost = 0; //cost when 1st num is odd
        int oddMin = Integer.MAX_VALUE;
        int oddMax = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if ((i & 1) != (nums[i] & 1)) {
                evenCost++;
                evenMin = Math.min(evenMin, nums[i] + 1);
                evenMax = Math.max(evenMax, nums[i] - 1);
                oddMin = Math.min(oddMin, nums[i]);
                oddMax = Math.max(oddMax, nums[i]);
            } else {
                oddCost++;
                oddMin = Math.min(oddMin, nums[i] + 1);
                oddMax = Math.max(oddMax, nums[i] - 1);
                evenMin = Math.min(evenMin, nums[i]);
                evenMax = Math.max(evenMax, nums[i]);
            }
        }
        final int evenRange = Math.max(evenMax - evenMin, 1);
        final int oddRange = Math.max(oddMax - oddMin, 1);
        if (evenCost < oddCost) {
            return new int[]{evenCost, evenRange};
        } else if (evenCost > oddCost) {
            return new int[]{oddCost, oddRange};
        } else {
            return new int[]{oddCost, Math.min(oddRange, evenRange)};
        }
    }
}