class Solution {
    public int maximumTripletValue(int[] nums) {
        List<Integer> idx = new ArrayList<>();
        for (int i = 0; i < nums.length; i++)
            idx.add(i);
        idx.sort((a, b) -> {
            var c = Integer.compare(nums[a], nums[b]);
            return c != 0 ? c : Integer.compare(b, a);
        });        int[] left = new int[nums.length];
        Arrays.fill(left, -1);
        // 1 4 2 3 5
        Deque<Integer> ascStack = new ArrayDeque<>();
        ascStack.add(idx.get(0));
        for (int i = 1; i < idx.size() - 1; i++) {
            while (!ascStack.isEmpty() && ascStack.peekLast() > idx.get(i)) {
                ascStack.pollLast();
            }
            if(!ascStack.isEmpty()){
                left[idx.get(i)] = nums[ascStack.peekLast()];
            }
            ascStack.addLast(idx.get(i));
        }

        int[] right = new int[nums.length];
        var max = nums[nums.length - 1];
        for (int j = nums.length - 2; j > 0; j--) {
            right[j] = max > nums[j] ? max : -1;
            max = Math.max(max, nums[j]);
        }
        int res = -1;
        for (int i = 1; i < nums.length - 1; i++) {
            if (left[i] != -1 && right[i] != -1)
                res = Math.max(res, right[i] + left[i] - nums[i]);
        }
        return res;
    }
}