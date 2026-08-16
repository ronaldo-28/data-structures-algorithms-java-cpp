










import java.util.AbstractList; 

class Solution {
        private List<Long> res; 

        public List<Long> minOperations(int[] nums, int[] queries) {
                return new AbstractList<Long>() {
                        @Override 
                        public int size() {
                                init(); 
                                return res.size(); 
                        }
                        @Override 
                        public Long get(int index) {
                                init(); 
                                return res.get(index); 
                        }
                        protected void init() {
                                if (res != null) 
                                        return; 
                                
                                res = new ArrayList<Long>(); 
                                Arrays.sort(nums); 

                                int N = nums.length; 
                                long[] presum = new long[N]; 
                                presum[0] = nums[0]; 
                                for (int i = 1; i < N; i += 1) {
                                        presum[i] = nums[i] + presum[i-1];  
                                }

                                for (int q: queries) {
                                        int ind = lowerBound(nums, q); 

                                        long tempRes = 0L; 
                                        if (ind == -1) {
                                                tempRes = presum[N - 1] - (long)q * N; 
                                        } else {
                                                tempRes = (long)q * (ind + 1) - presum[ind]; 
                                                tempRes += presum[N-1] - presum[ind] - (long)q * (N - ind - 1); 
                                        }
                                        res.add(tempRes); 
                                }
                        }
                }; 
        }
        private int lowerBound(int[] nums, int target) {
                int low = 0; 
                int high = nums.length - 1; 
                int res = -1; 
                while(low <= high) {
                        int mid= low + (high - low) / 2; 
                        if (nums[mid] >= target) {
                                high = mid - 1; 
                        } else {
                                res = mid; 
                                low = mid + 1; 
                        }
                }
                return res; 
        }
} 