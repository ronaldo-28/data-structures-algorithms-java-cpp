class Solution {
    record Block(int l, int r, int[] count, int delta){}
    public int[] numberOfPairs(int[] nums1, int[] nums2, int[][] queries) {
        int q = 0;
        int max = 0;
        for(int[] query:queries){
            if(query[0] == 1) continue;
            q++;
            max = Math.max(max, query[1]);
        }

        int[] ans = new int[q];
        if(q == 0) return ans;
        
        int m = nums1.length, n = nums2.length;

        int blockSize = m * (int)Math.sqrt(n);
        Block[] blocks = new Block[(n + blockSize - 1) / blockSize];

        for(int l = 0; l < n; l += blockSize){
            int r = Math.min( l + blockSize, n) - 1;
           int[] count = new int[max + 1];
            for(int i = l; i <= r; ++i){
                if(nums2[i] >= max) continue;
                count[nums2[i]]++;
            }

            blocks[l / blockSize] = new Block(l, r, count, 0);
        }
        
        int index = 0;

        for(int[] query:queries){
            if(query[0] == 1){

                int l = query[1], r = query[2], v = query[3];
                int start = l / blockSize, end = r / blockSize;
                for(int i = start; i <= end; ++i){

                    Block block = blocks[i];
                    if(block.delta >= max) continue;

                    if(block.l >= l && block.r <= r){
                        blocks[i] = new Block(block.l, block.r, block.count, Math.min(block.delta + v, max));
                        continue;
                    }

                    for(int j = Math.max(block.l, l); j <= Math.min(block.r, r); ++j ){
                        if(nums2[j] >= max) continue;
                        block.count[nums2[j]]--;
                        nums2[j] = Math.min(nums2[j] + v, max);
                        if(nums2[j] >= max) continue;
                        block.count[nums2[j]]++;
                    }
                }
            }else{

                int sum = query[1];
                int pairs = 0;
                for(Block block:blocks){
                    int target = sum - block.delta;
                    if(target <= 0) continue;{

                        for(int x:nums1){
                            if(target - x <= 0) continue;
                            pairs += block.count[target - x];
                        }
                    }

                }

                ans[index++] = pairs;
            }
        }

        return ans;
    }
}