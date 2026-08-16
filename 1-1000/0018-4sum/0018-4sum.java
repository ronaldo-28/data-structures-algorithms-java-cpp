import java.util.*;
class Solution {
    private List<List<Integer>> result;
    public List<List<Integer>> fourSum(int[] nums, int target) {
        return new AbstractList<List<Integer>>() {
            public List<Integer> get(int index) {
                init();
                return result.get(index);
            }

            public int size() {
                init();
                return result.size();
            }

            private void init(){ 
                List<List<Integer>> res = new ArrayList<>();
                Set<List<Integer>> resSet = new HashSet<>();
                int n =  nums.length;
                Arrays.sort(nums);
                for(int i = 0; i < n - 3; i++){
                    for(int j = i+1; j < n - 2; j++){
                        long newTarget = (long) target - (long) nums[i] - (long) nums[j]; 
                        int low = j+1, high = n-1;
                        while(low < high){

                            if(nums[low] + nums[high] == newTarget){
                                resSet.add(Arrays.asList(nums[low] , nums[high] , nums[i] , nums[j]));
                                while(low<high && nums[low] == nums[low+1]) low++;
                                while(low<high && nums[high] == nums[low-1]) high--;
                                low++;
                                high--;
                            }
                            else if(nums[low] + nums[high] < newTarget){
                                low++;
                            }else{
                                high--;
                            }
                        }
                    }
                }
                res.addAll(resSet);
                result = res;
            }
        };
    }
}