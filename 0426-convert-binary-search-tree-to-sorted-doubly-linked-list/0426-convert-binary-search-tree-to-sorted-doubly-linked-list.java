/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/

class Solution {
	Node head;
	Node tail;

	public Node treeToDoublyList(Node root) {
		if (root == null)
			return null;
		dfs(root);
		tail.right = head;
		head.left = tail;
		return head;
	}

	private void dfs(Node node) {
		if (node == null)
			return;

		dfs(node.left);
		
		// head only need to be dealt with once
		if (head == null)
			head = node;
		// for tail, keep moving the tail right. if the tail ptr is already assigned, need to attach curNode as new tail. Then reassign tail
		if (tail != null) {
			tail.right = node;
			node.left = tail;        
		} 
		tail = node;

		dfs(node.right);
	}
}