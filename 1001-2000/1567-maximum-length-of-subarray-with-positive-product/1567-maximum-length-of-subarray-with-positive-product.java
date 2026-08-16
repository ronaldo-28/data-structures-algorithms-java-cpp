class Solution {
    public int getMaxLen(int[] nums) {
        int n = nums.length;
        int max = 0;

        int i = 0;

        while(i < n){
            int neg = 0, firstNeg = -1 , lastNeg = n;
            int total = 0;
            int j = i;
            while(j < n && nums[j] != 0){
                if(nums[j] < 0){
                    if(firstNeg == -1) firstNeg = j;
                    lastNeg = j;

                    neg++;
                }
                total++;
                j++;
            }

            // System.out.println(i + " " + j + " " + " " + firstNeg + " " + lastNeg + " " + neg);

            if(neg % 2 == 0){
                max = Math.max(max, total);
            }else{
                max = Math.max(max, total - (firstNeg - i + 1));
                max = Math.max(max, total - (j - lastNeg));
            }
            i = j + 1;
        }

        return max;
    }
}