class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        return new java.util.AbstractList(){
            List<List<Integer>> result;
            private void init(){
                result = new ArrayList();
                backtrack(0, target, candidates, new ArrayList(), result);
            }

            @Override
            public int size(){
                if(result == null){
                    init();
                }
                return result.size();
            }

            @Override
            public List<Integer> get(int position){
                return result.get(position);
            }
        };
    }

    private void backtrack(int index, int target, int[] candidates, List<Integer> list, List<List<Integer>> result){
        if(target == 0){
            result.add(new ArrayList(list));
            return;
        }
        for(int i = index; i < candidates.length; i++){
            int remain = target - candidates[i];
            if(remain >= 0){
                list.add(candidates[i]);
                backtrack(i, remain, candidates, list, result);
                list.remove(list.size() - 1);
            }
        }
    }
}