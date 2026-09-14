class Solution {
    public int maximumProduct(int[] nums, int k) {
        //keyword: Assume min(nums) = a, max(nums) = d. (a + 1)bcd > (abc)(d + 1). Therefore increment the min(nums) each time leads to maximum ans
        // long ans = 1;
        // PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // for(int num : nums){
        //     minHeap.offer(num);
        // } 
        // while(k-- > 0){
        //     minHeap.offer(minHeap.poll() + 1);
        // }
        // while(!minHeap.isEmpty()){
        //     ans = ans * minHeap.poll() % 1_000_000_007;
        // }
        // return (int) ans;

        //Optimize: Group operation due to consistent focus on minimum 
        Arrays.sort(nums);
        long MOD = 1_000_000_007;
        int n = nums.length;
        int min = nums[0];
        int cnt = 1;
        
        for(int i = 1; i < n; i++){
            long diff = nums[i] - min;
            if(diff * i <= k){
                k -= (int) (diff * i);
                min = nums[i];
                cnt = i + 1;
            }else{
                break;
            }
        }
        long ans = 1;
        if(k > 0){
            min += k / cnt;
            int remainder = k % cnt;
            for(int i = 0; i < remainder; i++){
                ans = ans * (min + 1) % MOD;
            }
            for(int i = 0; i < cnt - remainder; i++){
                ans = ans * min % MOD;
            }
        }else{
            for(int i = 0; i < cnt; i++){
                ans = ans * min % MOD;
            }
        }
        for(int i = cnt; i < n; i++){
            ans = ans *nums[i] % MOD;
        }
        return (int) ans;
    }
}