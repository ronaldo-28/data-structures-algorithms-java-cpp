class Solution {
    public List<Integer> goodIndices(String s) {
        List<Integer> ans = new ArrayList<>();
        int pow = 10, size = 1, n = s.length(), val = s.charAt(0) - '0';

        if(val == 0) ans.add(0); //process the 0th index seperately
        for(int i = 1; i < n; i++) {
            val = 10 * val + s.charAt(i) - '0'; //add new digit to the window

            if(i == pow) { //if index is the current power of 10
                pow *= 10; //go to next power of 10
                size++; //increment window size
            }else val -= pow * (s.charAt(i - size) - '0'); //otherwise remove the leftmost digit from the window

            if(val == i) ans.add(i); //if the current value equals the index, add it to the answer
        }
        return ans;
    }
}