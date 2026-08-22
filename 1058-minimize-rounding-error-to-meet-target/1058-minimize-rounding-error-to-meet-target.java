class Solution {
    public String minimizeError(String[] prices, int target) {
        int n = prices.length;

        int decimalCount = 0;
        //stores only decimal values, 7.45 would be stored as 0.45, 9.123 would be 0.123, etc
        double[] decimals = new double[n];

        int floorSum = 0; //total sum of all the whole numbers without their decimals
        double totalDecimalSum = 0; //total sum of all decimals values, no whole numbers

        for(int i = 0; i < n; i++) {
            double actualVal = Double.parseDouble(prices[i]);
            int floorVal = (int)actualVal;

            floorSum += floorVal;
            
            double diff = actualVal - floorVal;
            //if actualVal isn't already a whole number, put the fraction part in the decimals array
            if(diff != 0) {
                totalDecimalSum += diff;
                decimals[decimalCount++] = diff;
            }
        }
        //we can return early if floorSum = target
        if(target == floorSum) return String.format("%.3f", totalDecimalSum);


        //target = floorSum + count of ceiled elements
        //count of ceiled elements = target - floorSum
        int ceilCount = target - floorSum;

        //if floorSum > target, sum is always too large
        //if ceilCount > decimalCount, we can't reach target even if we ceil all elements
        if(ceilCount < 0 || ceilCount > decimalCount) return "-1";
        
        //partition the array into two sections, the (decimalCount - ceilCount) smallest fractions, and the (ceilCount) largest fractions
        quickselect(decimals, 0, decimalCount - 1, decimalCount - ceilCount);

        //sum up the (ceilCount) largest decimals, as they are closest to 1.0
        double totalLargerSum = 0;
        for(int i = decimalCount - ceilCount; i < decimalCount; i++) totalLargerSum += decimals[i];

        //for each of the larger decimals 'val': error += 1 - val
        //for each of the smaller decimals 'val': error += val
        //this can be simplified to the following: 
        //error = (totalSmallerSum) + (ceilCount - totalLargerSum)
        //error = (totalDecimalSum - totalLargerSum) + (ceilCount - totalLargerSum)
        //error = totalDecimalSum + ceilCount - 2 * totalLargerSum
        double error = totalDecimalSum + ceilCount - 2.0 * totalLargerSum;
        return String.format("%.3f", error);
    }

    
    private static double quickselect(double[] nums, int left, int right, int k) {
        while(left < right) {
            int part = partition(nums, left - 1, right + 1);
            if(part < k) left = part + 1;
            else right = part;
        }
        return nums[k];
    }
    private static int partition(double[] nums, int left, int right) {
        double current = getPivot(nums[left + 1], nums[left + right >>> 1], nums[right - 1]), temp = 0;
        while(true) {
            do {
                left++;
            }while(nums[left] < current);
            do {
                right--;
            }while(nums[right] > current);
            
            if(left >= right) return right;

            temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }
    }
    private static double getPivot(double a, double b, double c) {
        if((a >= b) ^ (a >= c)) return a;
        if((a >= b) ^ (c >= b)) return b;
        return c;
    }
}