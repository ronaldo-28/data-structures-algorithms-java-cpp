/** 
 * Definition of commonBits API (defined in the parent class Problem).
 * int commonBits(int num);
 */
// time = O(1), space = O(1)
public class Solution extends Problem {
    public int findNumber() {
        int res = 0;
        int z = commonBits((1 << 30) - 1);
        for (int i = 0; i <= 30; i++) {
            int t = commonBits(1 << i);
            if (t == z - 1) {
                res |= 1 << i;
                z--;
            } else z++;
        }
        return res;
    }
}