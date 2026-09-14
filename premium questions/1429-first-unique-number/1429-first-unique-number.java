class FirstUnique {
    LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
    Set<Integer> removed = new HashSet<>();
    public FirstUnique(int[] nums) {
        for (int num : nums) {
            add(num);
        }
    }
    
    public int showFirstUnique() {
        if (!linkedHashSet.isEmpty()) return linkedHashSet.getFirst();
        return -1;
    }
    
    public void add(int value) {
        if (linkedHashSet.contains(value)) {
            linkedHashSet.remove(value);
            removed.add(value);
        } else if (!removed.contains(value)) {
            linkedHashSet.addLast(value);
        }
    }
}

/**
 * Your FirstUnique object will be instantiated and called as such:
 * FirstUnique obj = new FirstUnique(nums);
 * int param_1 = obj.showFirstUnique();
 * obj.add(value);
 */