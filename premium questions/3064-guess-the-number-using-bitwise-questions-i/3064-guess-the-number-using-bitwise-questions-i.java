/** 
 * Definition of commonSetBits API (defined in the parent class Problem).
 * int commonSetBits(int num);
 */

// start 11:17pm
// solve 11:29pm

public class Solution extends Problem {
    public int findNumber() {
        int res = 0;  // what we'll return
        int maxNum = 1 << 29;  // largest binary number less than 2^30 - 1

        for (int curCandidateNum = 1; curCandidateNum <= maxNum; curCandidateNum = curCandidateNum << 1) {
            if (commonSetBits(curCandidateNum) == 1) {
                res += curCandidateNum;
            }
        }

        return res;
    }
}