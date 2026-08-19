class Solution {
    public boolean sequenceReconstruction(int[] nums, List<List<Integer>> sequences) {
        boolean[] cache = new boolean[nums.length-1];
        int[] pos = new int[nums.length];
        for (int i=0; i<nums.length; ++i) 
            pos[nums[i]-1] = i;

        for (List<Integer> seq : sequences) {
            for (int i=0; i<seq.size()-1; ++i)
                if (pos[seq.get(i)-1] + 1 == pos[seq.get(i+1)-1])
                    cache[pos[seq.get(i)-1]] = true;
        }

        for (boolean c : cache)
            if (!c) return false;
        return true;
    }
}