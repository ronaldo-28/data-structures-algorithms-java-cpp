class Solution {

    private void combinationSumUtil(int[] arr, int idx, int sum, List<Integer> tmp, List<List<Integer>> list) {
        if (sum == 0) {
            list.add(new ArrayList<Integer>(tmp));
            return;
        }

        for (int i = idx; i < arr.length; i++) {
            if (i > idx && arr[i - 1] == arr[i]) continue;

            if (arr[i] > sum) break;

            System.out.println("CombinationSumUtil i at := " + i 
            + " when idx := " + idx + " arr[" + i + "] := " + arr[i]);

            int remain = sum - arr[i];

            tmp.add(arr[i]);
            combinationSumUtil(arr, i + 1, remain, tmp, list);
            tmp.remove(tmp.size() - 1);
        }
    }

    private void getResults(int[] arr, int sum, List<List<Integer>> result) {
        Arrays.sort(arr);
        Arrays.stream(arr).forEach(a -> System.out.print(a + " "));
        System.out.println();
        combinationSumUtil(arr, 0, sum, new ArrayList<Integer>(), result);
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        return new java.util.AbstractList() {
            List<List<Integer>> result;

            private void init() {
                result = new ArrayList<List<Integer>>();
                getResults(candidates, target, result);
            }

            @Override
            public int size() {
                if (result == null) {
                    init();
                }

                return result.size();
            }

            @Override
            public List<Integer> get(int pos) {
                return result.get(pos);
            }
        };
    }
}