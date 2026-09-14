/**
 * Definition for a binary tree node.
 * class Node {
 *     char val;
 *     Node left;
 *     Node right;
 *     Node() {this.val = ' ';}
 *     Node(char val) { this.val = val; }
 *     Node(char val, Node left, Node right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private String s;
    private int i;
    
    public Node expTree(String s) {
        this.s = s;
        this.i = 0;
        return parseExpression();
    }
    
    private Node parseExpression(){
        Node node = parseTerm();
        while(i < s.length() && 
             (s.charAt(i) == '+' || s.charAt(i) == '-')){
            
            char op = s.charAt(i++);
            Node right = parseTerm();
            node = new Node(op, node, right);
        }
        return node;
    }
    
    private Node parseTerm() {
        Node node = parseFactor();
        
        while(i < s.length() &&
             (s.charAt(i) == '*' || s.charAt(i) == '/')){
            char op = s.charAt(i++);
            Node right = parseFactor();
            
            node = new Node(op, node, right);
        }
        return node;
    }
    
    private Node parseFactor(){
        char ch = s.charAt(i);
        
        if(ch == '('){
            i++;
            Node node = parseExpression();
            i++;
            return node;
        }
        i++;
        return new Node(ch);
    }
}