class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if(nums.length==0) return res;
        StringBuffer sb = new StringBuffer();
        for(int i=1; i<nums.length; i++) {
            if(nums[i]==nums[i-1]+1) {
                if(sb.isEmpty()) {
                    sb.append(nums[i-1]);
                    sb.append("->");
                }
            } else {
                sb.append(nums[i-1]);
                res.add(sb.toString());
                sb = new StringBuffer();
            }
        }
        sb.append(nums[nums.length-1]);
        res.add(sb.toString());
        return res;
    }
}