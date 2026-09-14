class Solution {
    int idx = 0;
    public boolean verifyPreorder(int[] preorder) {
        helper(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return idx == preorder.length;
    }

    // pre order de-serialization and check iff everything is de-serialized
    private void helper(int[] preorder, int min, int max) {
        if (idx == preorder.length) {
            return;
        }
        if (preorder[idx] > min && preorder[idx] < max) {
            int rootVal = preorder[idx++];
            helper(preorder, min, rootVal);
            helper(preorder, rootVal, max);
        } else {
            return;
        }
    }
}










    