class Solution {
    public int minDeletion(int[] A) {
        int res = 0, pre = -1;
        for (int a : A) {
            if (a == pre)
                res++;
            else
                pre = pre < 0 ? a : -1;
        }
        return pre < 0 ? res : res + 1;
    }
}