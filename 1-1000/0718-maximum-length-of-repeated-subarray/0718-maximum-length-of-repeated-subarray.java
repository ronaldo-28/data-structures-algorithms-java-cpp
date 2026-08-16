class Solution {
    long[]powers;
    public int findLength(int[] nums1, int[] nums2) {
        int low=1,high=Math.min(nums1.length,nums2.length);
        while(low<=high){
            int mid=low+(high-low)/2;
            if(isPossible(nums1,nums2,mid)){
                low=mid+1;
            }
            else {
                high=mid-1;
            }
        }
        return high;
    }
    public boolean isPossible(int[] nums1, int[] nums2, int k) {
    if (k > nums1.length || k > nums2.length) return false;

    int base = 101;

    HashSet<Integer> set = new HashSet<>();
    int hash = 0;
    int pow = 1;

    for (int i = 1; i < k; i++) pow *= base;

    for (int i = 0; i < k; i++) hash = hash * base + nums1[i];
    set.add(hash);

    for (int i = k; i < nums1.length; i++) {
        hash = hash - nums1[i - k] * pow;   
        hash = hash * base + nums1[i];      
        set.add(hash);
    }

    hash = 0;

    for (int i = 0; i < k; i++) hash = hash * base + nums2[i];
    if (set.contains(hash)) return true;

    for (int i = k; i < nums2.length; i++) {
        hash = hash - nums2[i - k] * pow;
        hash = hash * base + nums2[i];

        if (set.contains(hash)) return true;
    }

    return false;
}

}