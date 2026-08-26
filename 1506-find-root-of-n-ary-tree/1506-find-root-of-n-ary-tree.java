/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    
    public Node() {
        children = new ArrayList<Node>();
    }
    
    public Node(int _val) {
        val = _val;
        children = new ArrayList<Node>();
    }
    
    public Node(int _val,ArrayList<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public Node findRoot(List<Node> tree) {
        int xorValue = 0;
        
        // Pass 1: XOR every node's value and every child's value
        for (Node node : tree) {
            xorValue ^= node.val;
            for (Node child : node.children) {
                xorValue ^= child.val;
            }
        }
        
        // Pass 2: Find the node object that matches the remaining XOR value
        for (Node node : tree) {
            if (node.val == xorValue) {
                return node;
            }
        }
        
        return null;
    }
}