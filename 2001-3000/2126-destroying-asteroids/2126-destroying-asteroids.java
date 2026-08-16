class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        int min = 100000;
        int max = 0;
        for(int x : asteroids) {
            max = Math.max(x, max);
            min = Math.min(x, min);
        }

        int[] freq = new int[max + 1];
        long val = mass;
        for(int x : asteroids) {
            if(x > val) freq[x]++;
            else val += x;
        }
        if(val >= max) return true;
        for(int i = min; i <= max; i++) {
            if(val < i) return false;
            if(freq[i] != 0) val += i * freq[i];
        }
        return true;
    }
}