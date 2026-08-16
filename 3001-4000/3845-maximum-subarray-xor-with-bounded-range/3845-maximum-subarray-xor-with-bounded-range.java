class Solution {
    private final TrieNode root = new TrieNode();
    public int maxXor(int[] nums, int k) {
        int n = nums.length, current = 0, ans = 0;
        int[] min = new int[n], max = new int[n], prefix = new int[n + 1];
        int top1 = -1, top2 = -1, left = 0, bottom1 = 0, bottom2 = 0;
        insert(0);
        for(int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] ^ nums[i];
            while(top1 >= bottom1 && nums[min[top1]] >= nums[i]) top1--;
            while(top2 >= bottom2 && nums[max[top2]] <= nums[i]) top2--;
            min[++top1] = max[++top2] = i;
            while(nums[max[bottom2]] - nums[min[bottom1]] > k) {
                if(min[bottom1] == left) bottom1++;
                if(max[bottom2] == left) bottom2++;
                remove(prefix[left++]);
            }
            ans = Math.max(ans, insert(prefix[i + 1]));
        }
        return ans;
    }
    private int insert(int num) {
        TrieNode current1 = root, current2 = root;
        int ans = 0;
        for(int i = 15; i >= 0; i--) {
            int val = num >>> i & 1;
            if(current1.next[val] == null) current1.next[val] = new TrieNode();
            current1 = current1.next[val];
            current1.count++;

            if(current2.next[val ^ 1] == null) current2 = current2.next[val];
            else {
                current2 = current2.next[val ^ 1];
                ans |= 1 << i;
            }
        }
        return ans;
    }
    private void remove(int num) {
        TrieNode current = root;
        for(int i = 15; i >= 0; i--) {
            int val = num >>> i & 1;
            if(--current.next[val].count == 0) {
                current.next[val] = null;
                return;
            }
            current = current.next[val];
        }
    }
    private static final class TrieNode {
        private final TrieNode[] next = new TrieNode[2];
        private int count = 0;
    }
}