/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Codec {
    // Encodes an n-ary tree to a binary tree.
    public TreeNode encode(Node root) {
        if(root==null){
            return null;
        }
        TreeNode binaryRoot=new TreeNode(root.val);
        if(root.children.size() == 0){
            return binaryRoot;
        }
        binaryRoot.left=encode(root.children.get(0));
        TreeNode current=binaryRoot.left;
        for(int i=1;i<root.children.size();i++){
            current.right=encode(root.children.get(i));
            current=current.right;
        }
        return binaryRoot;
    }
	
    // Decodes your binary tree to an n-ary tree.
    public Node decode(TreeNode root) {
        if(root==null){
            return null;
        }
        Node naryRoot=new Node(root.val);
        naryRoot.children=new ArrayList<>();
        TreeNode child=root.left;
        while(child!=null){
            naryRoot.children.add(decode(child));
            child=child.right;
        }
        return naryRoot;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(root));