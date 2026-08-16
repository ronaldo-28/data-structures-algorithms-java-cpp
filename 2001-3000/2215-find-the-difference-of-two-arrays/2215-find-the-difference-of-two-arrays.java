import java.util.AbstractList;

class Solution {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        return new AbstractList<>() {
            List<Integer> res1, res2;

            private void init() {
                res1 = new ArrayList<>();
                res2 = new ArrayList<>();
                boolean[] v1 = new boolean[2001];
                boolean[] v2 = new boolean[2001];
                for (int num : nums1)
                    v1[num + 1000] = true;
                for (int num : nums2)
                    v2[num + 1000] = true;

                for (int num : nums1) {
                    if (!v2[num + 1000]) {
                        res1.add(num);
                        v2[num + 1000] = true;
                    }
                }

                for (int num : nums2) {
                    if (!v1[num + 1000]) {
                        res2.add(num);
                        v1[num + 1000] = true;
                    }
                }
            }

            @Override
            public List<Integer> get(int index) {
                if (res1 == null)
                    init();
                if (index == 0)
                    return res1;
                return res2;
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }
}