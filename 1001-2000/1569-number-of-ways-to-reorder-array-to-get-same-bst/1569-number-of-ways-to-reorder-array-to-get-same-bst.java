class Solution {
    static final int MAX = 1000;
	static final int MOD = 1_000_000_007;
	static final TreeNode[] q = new TreeNode[MAX];
	static final long[] mults = new long[MAX + 1];
	static {
		mults[0] = 1;
		for (int i = 1; i <= MAX; i++) 
			mults[i] = (mults[i - 1] * i) % MOD;
	}

	public int numOfWays(int[] nums) {
		TreeNode root = new TreeNode(nums[0]);
		for (int i = 1; i < nums.length; i++) {
			root.add(nums[i]);
		}
		int len = 0;
		q[len++] = root;
		long d = 1;
		for (int i = 0; i < len; i++) {
			final TreeNode node = q[i];
			d = (d * node.count) % MOD;
			if (node.left != null)
				q[len++] = node.left;
			if (node.right != null)
				q[len++] = node.right;
		}
		return (int) ((mults[len] * inverse(d) + MOD - 1) % MOD);
	}

	static long inverse(long y) {
		long e = MOD - 2, a = 1;
		while (e > 0) {
			if (e % 2 == 1) {
				a = (a * y) % MOD;
			}
			e /= 2;
			y = (y * y) % MOD;
		}
		return a;
	}
	
	static final class TreeNode {
		int val;
		int count;

		TreeNode left;
		TreeNode right;

		public TreeNode(int val) {
			this.val = val;
			this.count = 1;
		}

		void add(int val) {
			for (TreeNode node = this;;) {
				node.count++;
				final TreeNode next = val < node.val ? node.left : node.right;
				if (next == null) {
					if (val < node.val) {
						node.left = new TreeNode(val);
					} else {
						node.right = new TreeNode(val);
					}
					break;
				} else {
					node = next;
				}
			}
		}
	}
}