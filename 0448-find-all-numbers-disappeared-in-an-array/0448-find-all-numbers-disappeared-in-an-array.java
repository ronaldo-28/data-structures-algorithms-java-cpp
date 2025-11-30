class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        return new MyList(nums);
    }

    class MyList extends ArrayList<Integer> {
        int[] nums;

        MyList(int[] nums) {
            this.nums = nums;
        }

        @Override
        public int size() {
            method();
            return super.size();
        }

        private void method() {
            for (int i = 0; i < nums.length; i++) {
                int index = Math.abs(nums[i]) - 1;
                if (nums[index] > 0) {
                    nums[index] = -nums[index];
                }
            }
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > 0) {
                    super.add(new Integer(i + 1));
                }
            }
        }
    }
}