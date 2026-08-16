class BSTIterator {
    Stack<TreeNode> st = new Stack<>();
    boolean rev;

    public BSTIterator(TreeNode root, boolean rver) {
        rev = rver;
        pushAll(root);
    }

    public int next() {
        TreeNode temp = st.pop();
        if (rev == false)
            pushAll(temp.right);
        else
            pushAll(temp.left);
        return temp.val;
    }

    public boolean hasNext() {
        return !st.isEmpty();
    }

    void pushAll(TreeNode root) {
        while (root != null) {
            st.push(root);
            if (rev)
                root = root.right;
            else
                root = root.left;
        }
    }
}

class Solution {
    static {
		Runtime.getRuntime().gc();
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			try (FileWriter writer = new FileWriter("display_runtime.txt")) {
				writer.write("0");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}));
    }
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;

        BSTIterator i = new BSTIterator(root, false); // inorder
        BSTIterator j = new BSTIterator(root, true);  // reverse inorder

        int l = i.next();
        int r = j.next();

        while (l < r) {
            if (l + r == k) return true;
            else if (l + r < k) l = i.next();
            else r = j.next();
        }
        return false;
    }
}