class TwoSum {
    private final int[] freq;
    private int max, min;
    public TwoSum() {
        freq = new int[200001];
        max = -1;
        min = 200001;
    }
    
    public void add(int number) {
        int num = number + 100000;
        freq[num]++;
        max = Math.max(max, num);
        min = Math.min(min, num);
    }
    
    public boolean find(int value) {
        if(value < 2 * (min - 100000) || value > 2 * (max - 100000)) return false;
        if(value % 2 == 0 && freq[value / 2 + 100000] > 1) return true;
        int left = min, right = max;
        while(true) {
            while(left < right && freq[left] == 0) left++;
            while(left < right && freq[right] == 0) right--;
            if(left == right) return false;
            int sum = left + right - 200000;
            if(sum == value) return true;
            if(sum < value) left++;
            else right--;
        }
    }
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */