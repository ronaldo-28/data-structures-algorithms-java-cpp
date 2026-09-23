class Solution {
    public String triangleType(int[] nums) {
        int firstSide = nums[0];
        int secondSide = nums[1];
        int thirdSide = nums[2];
        if (firstSide + secondSide <= thirdSide || secondSide + thirdSide <= firstSide
                || thirdSide + firstSide <= secondSide) {
            return "none";
        }
        if (firstSide == secondSide && secondSide == thirdSide && thirdSide == firstSide) {
            return "equilateral";
        }
        if (firstSide == secondSide || secondSide == thirdSide || thirdSide == firstSide) {
            return "isosceles";
        } else {
            return "scalene";
        }

    }
}