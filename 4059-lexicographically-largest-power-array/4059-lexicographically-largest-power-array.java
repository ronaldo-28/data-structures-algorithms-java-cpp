class Solution {
    private static final class Group {
        private int left, right, and;
        private Group next;
        private Group(int left, int right, int and, Group next) {
            this.left = left;
            this.right = right;
            this.and = and;
            this.next = next;
        }
    }
    public int[] largestPower(int[] nums) {
        int n = nums.length;

        int total = 0, and = Integer.MAX_VALUE;
        for(int num : nums) {
            total |= num;
            and &= num;
        }

        Group head = new Group(0, n - 1, and, null);

        int[] ans = new int[15];
        for(int bitPos = 14; bitPos >= 0; bitPos--) {
            int currentBit = 1 << bitPos;
            if((total & currentBit) == 0) continue;
            total ^= currentBit;

            //find first group with elements that don't have the current bit
            Group currentGroup = head;
            while(currentGroup != null && (currentGroup.and & currentBit) != 0) currentGroup = currentGroup.next;

            if(currentGroup == null) {
                ans[14 - bitPos] = n;
                continue;
            }
            
            int and1 = Integer.MAX_VALUE, and2 = Integer.MAX_VALUE;
            int index = currentGroup.left;
            for(int i = index; i <= currentGroup.right; i++) {
                if((nums[i] & currentBit) != 0) {
                    and1 &= nums[i];

                    int temp = nums[index];
                    nums[index++] = nums[i];
                    nums[i] = temp;
                }else and2 &= nums[i];
            }

            ans[14 - bitPos] = index;
            if(index != currentGroup.left) {
                currentGroup.next = new Group(index, currentGroup.right, and2, currentGroup.next);
                currentGroup.right = index - 1;
                currentGroup.and = and1;
            }

        }

        //System.out.println()
        return ans;
    }
}