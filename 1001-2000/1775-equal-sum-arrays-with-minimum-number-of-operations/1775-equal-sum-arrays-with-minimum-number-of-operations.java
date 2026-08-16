class Solution {
    public int minOperations(int[] nums1, int[] nums2) {
        int[] c1 = new int[7];
        int[] c2 = new int[7];
        int sum1 = getSum(nums1, c1);
        int sum2 = getSum(nums2, c2);
        if(sum1 > sum2){
            return minOperations(nums2, nums1);
        }else if(sum1 == sum2){
            return 0;
        }
        int res = check(c1, c2, sum2 - sum1);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public int getSum(int[] arr, int[] c){
        int sum = 0;
        for(int num : arr){
            sum += num;
            c[num]++;
        }
        return sum;
    }

    public int check(int[] c1, int[] c2, int d){
        int res = 0;
        for(int i = 1; i < 6; i++){
            if(c1[i] == 0 && c2[7 - i] == 0){
                continue;
            }
            if(d == 0){
                break;
            }
            if(c1[i] * (6 - i) >= d){
                res += Math.ceil(d / (double)(6 - i));
                d = 0;
            }else{
                res += c1[i];
                d -= c1[i] * (6 - i);
            }
            if(d > 0 && c2[7 - i] * (7 - i - 1) >= d){
                res += Math.ceil(d / (double)(6 - i));
                d = 0;
            }else if(d > 0){
                res += c2[7 - i];
                d -= c2[7 - i] * (7 - i - 1);
            }
        }
        return d != 0 ? Integer.MAX_VALUE : res;
    }
}