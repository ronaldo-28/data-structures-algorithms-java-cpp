class Solution {
    public int meetRequirement(int n, int[][] lights, int[] requirement) {
        int[] diff = new int[n];
        for(int[] light : lights) {
            int position = light[0];
            int range = light[1];
            int left = position - range;
            int right = position + range;
            if(left < 0) {
                diff[0]++;
            } else {
                diff[left]++;
            }

            if(right+1 < n) {
                diff[right+1]--;
            }
        }

        int ans = 0;
        int brightness = 0;
        for(int i=0; i<n; i++) {
            brightness += diff[i];
            if(brightness >= requirement[i]) {
                ans++;
            }
        }
        return ans;
    }
}