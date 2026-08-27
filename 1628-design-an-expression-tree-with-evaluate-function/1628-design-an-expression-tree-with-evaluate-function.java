/**
 * This is the interface for the expression tree Node.
 * You should not remove it, and you can define some classes to implement it.
 */

abstract class Node {
    public abstract int evaluate();
    // define your fields here
    String val;
    TreeNode left;
    TreeNode right;
};

class TreeNode extends Node {
    
    public TreeNode(){};
    public TreeNode(String val) {
        this.val = val;
    }
    
    @Override
    public int evaluate() {
        return postorder(this);
    }
    
    private int postorder(TreeNode node) {
        if (node.left == null && node.right == null) {
            return Integer.parseInt(node.val);
        }
        
        int left = postorder(node.left);
        int right = postorder(node.right);
        
        String op = node.val;
        return switch(op) {
                case "+" -> left + right;
                case "-" -> left - right;
                case "*" -> left * right;
                case "/" -> left / right;
                default -> 0;
        };
    }
}


/**
 * This is the TreeBuilder class.
 * You can treat it as the driver code that takes the postinfix input 
 * and returns the expression tree representing it as a Node.
 */

class TreeBuilder {
    private Set<String> operators =
        new HashSet<>(List.of("+", "-", "*", "/"));
    private int index;
    
    Node buildTree(String[] postfix) {
        index = postfix.length-1;
        return build(postfix);
    }
    
    private TreeNode build(String[] postfix) {
        String val = postfix[index--];
        TreeNode root = new TreeNode(val);
        
        if (!isOperator(val)) {
            return root;
        }
        
        root.right = build(postfix);
        root.left = build(postfix);
        return root;
    }
    
    private boolean isOperator(String s) {
        return operators.contains(s);
    }
};


/**
 * Your TreeBuilder object will be instantiated and called as such:
 * TreeBuilder obj = new TreeBuilder();
 * Node expTree = obj.buildTree(postfix);
 * int ans = expTree.evaluate();
 */