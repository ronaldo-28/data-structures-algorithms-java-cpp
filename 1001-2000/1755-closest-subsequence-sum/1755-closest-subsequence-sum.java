class Solution1 {
    public int minAbsDifference(int[] nums, int goal) {
        int N = nums.length;
        int N1 = (1 << (N/2));
        int N2 = (1 << (N - N/2));
        List<Integer> list1 = new ArrayList<>(N1);
        List<Integer> list2 = new ArrayList<>(N2);
        generateSum(nums, 0, N/2-1, 0, list1);
        generateSum(nums, N/2, N-1, 0, list2);

        Collections.sort(list1);
        Collections.sort(list2);
        //System.out.println("list1=" + list1);
        //System.out.println("list2=" + list2);

        int min = 1000000000;
        int j = list2.size()-1;
        for(int i = 0; i < list1.size(); i++){
            int v1 = list1.get(i);
            int v2 = goal - v1;
            while(j > 0 && list2.get(j) > v2){
                min = Math.min(min, Math.abs(goal - list1.get(i) - list2.get(j)));
                j--;
            }
            min = Math.min(min, Math.abs(goal - list1.get(i) - list2.get(j)));
        } 
        return min;
    }

    private void generateSum(int[] nums, int i, int end, int curr, List<Integer> list){
        if(i > end){
            list.add(curr);
            return;
        }
        generateSum(nums, i+1, end, curr, list);
        generateSum(nums, i+1, end, curr+nums[i], list);
    }
}


class Solution {
    int minDiff = Integer.MAX_VALUE;
    
    public int minAbsDifference(int[] nums, int goal) {
        int posSum = IntStream.of(nums).filter(n -> n > 0).sum();
        int negSum = -IntStream.of(nums).filter(n -> n < 0).sum();
        int[] sorted = IntStream.of(nums).filter(n -> n != 0).boxed().sorted(Comparator.comparing(n -> -Math.abs(n))).mapToInt(i->i).toArray();
        
        solve(sorted, 0, posSum, negSum, goal);
        return minDiff;
    }
    
    public void solve(int[] nums, int i, int posSum, int negSum, int goal) {
        minDiff = Math.min(minDiff, Math.abs(goal));
        if (minDiff == 0) 
            return;
        if (i == nums.length || Math.max(posSum, negSum) < Math.abs(goal) - minDiff) 
            return;
        int v = nums[i];
        
        if (v > 0) 
            posSum -= v;
        else 
            negSum += v;
        
        solve(nums, i + 1, posSum, negSum, goal - v);
        solve(nums, i + 1, posSum, negSum, goal);
    }
}