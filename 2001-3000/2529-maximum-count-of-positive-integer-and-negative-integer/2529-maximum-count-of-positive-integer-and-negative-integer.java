class Solution {

    public static int search(int[] nums,int start,int end){
        int countNeg = 0,countPos = 0,countZero = 0;
         while(start<=end){
            int mid = end+(start-end)/2;
            if(nums[mid] == 0){
                countZero++;
                if(countZero == nums.length) return 0;
                if(mid > 0 && nums[mid-1] < 0){
                    countNeg = mid;
                    start = mid+1;
                }else if(mid < nums.length-1 && nums[mid+1] > 0){
                    countPos = nums.length-mid-1;
                    end = mid-1;
                }
                else if(nums[0] == 0 && nums[nums.length-1] != 0){
                    start = mid+1;
                }
                else if(nums[0] != 0 && nums[nums.length-1] == 0){
                    end = mid-1;
                }
                
            }
            else if(nums[mid] < 0){
                countNeg = mid+1;
                start = mid+1;
            }else if(nums[mid] > 0){
                countPos = nums.length-mid;
                end = mid-1;
            }
        }

        if(countNeg > countPos) return countNeg;  
        return countPos;
    }

    public int maximumCount(int[] nums) {
       int start = 0,end = nums.length-1;
       start = search(nums,start,end);
       return start;
    }
}