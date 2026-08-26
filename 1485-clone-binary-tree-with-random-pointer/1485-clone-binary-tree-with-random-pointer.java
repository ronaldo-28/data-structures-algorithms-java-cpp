/**
 * Definition for Node.
 * public class Node {
 *     int val;
 *     Node left;
 *     Node right;
 *     Node random;
 *     Node() {}
 *     Node(int val) { this.val = val; }
 *     Node(int val, Node left, Node right, Node random) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *         this.random = random;
 *     }
 * }
 */

class Solution {
    int[] origVals = new int[1000];             // Saved Node.val values from input tree.
    NodeCopy[] nodeCopies = new NodeCopy[1000]; // Ptrs to each NodeCopy in output tree.
    Node[] nodes = new Node[1000];              // Pointers to each Node in input tree.
    int nextNodeNum = 0;                        // Next val to put into next input Node.  
                                                // The Node.val becomes an index into the 
                                                // arrays nodes[] and nodeCopies[].
    
    public NodeCopy copyRandomBinaryTree(Node root) {
        // Create output tree and fill nodes[] and nodeCopies[] with pointers to all 
        // nodes in both the input and output trees.  Change the Node.val values in 
        // the input tree to be the Node's index into the nodes[] array.
        NodeCopy result = traverse(root);
        
        // Use the Node pointers in the nodes[] array, to get a pointer to each Node's 
        // .random Node.  The Node's .random node, if it exists, will have its Node.val 
        // value having been changed to the index of itself in the nodes[] and 
        // nodeCopies[] arrays.  Use this index and the nodeCopies[] array to link the 
        // NodeCopy .random links.  To create the output .random links, we just traverse 
        // the used portion of the nodes[] and nodeCopies[] arrays, instead of using a 
        // HashMap as suggested in the hints.  Arrays are faster than HashMaps.
        for (int i = 0; i < nextNodeNum; i++) 
            if (nodes[i].random != null)
                nodeCopies[i].random = nodeCopies[nodes[i].random.val];
        for (int i = 0; i < nextNodeNum; i++) 
            nodes[i].val = origVals[i];
        return result;
    }
    
    
    // Recursively deapth-first-traverse the input Node tree, while creating the 
    // corresponding output NodeCopy tree.  Not linking the NodeCopy .random pointers 
    // yet.  Fill the nodes[] and nodeCopies[] arrays while traversing.  Assign new 
    // sequential Node.val values into the input nodes, to be used as index values 
    // into the nodes[] and nodeCopies[] arrays later, for creating the NodeCopy 
    // .random links.
    private NodeCopy traverse(Node node) {
        if (node == null)  return null;
        NodeCopy copy = new NodeCopy(node.val);
        nodeCopies[nextNodeNum] = copy;
        nodes[nextNodeNum] = node;
        origVals[nextNodeNum] = node.val;
        node.val = nextNodeNum++;
        copy.left = traverse(node.left);
        copy.right = traverse(node.right);
        return copy;
    }
}


//-------------------------------------------------------------------------------------
// class Solution {
//     NodeCopy[] nodeCopies = new NodeCopy[1000]; // Ptrs to each NodeCopy in output tree.
//     Node[] nodes = new Node[1000];              // Pointers to each Node in input tree.
//     int nextNodeNum = 0;                        // Next val to put into next input Node.  
//                                                 // The Node.val becomes an index into the 
//                                                 // arrays nodes[] and nodeCopies[].
    
//     public NodeCopy copyRandomBinaryTree(Node root) {
//         // Create output tree and fill nodes[] and nodeCopies[] with pointers to all 
//         // nodes in both the input and output trees.  Change the Node.val values in 
//         // the input tree to be the Node's index into the nodes[] array.
//         NodeCopy result = traverse(root);
        
//         // Use the Node pointers in the nodes[] array, to get a pointer to each Node's 
//         // .random Node.  The Node's .random node, if it exists, will have its Node.val 
//         // value having been changed to the index of itself in the nodes[] and 
//         // nodeCopies[] arrays.  Use this index and the nodeCopies[] array to link the 
//         // NodeCopy .random links.  To create the output .random links, we just traverse 
//         // the used portion of the nodes[] and nodeCopies[] arrays, instead of using a 
//         // HashMap as suggested in the hints.  Arrays are faster than HashMaps.
//         for (int i = 0; i < nextNodeNum; i++) 
//             if (nodes[i].random != null)
//                 nodeCopies[i].random = nodeCopies[nodes[i].random.val];
//         return result;
//     }
    
    
//     // Recursively deapth-first-traverse the input Node tree, while creating the 
//     // corresponding output NodeCopy tree.  Not linking the NodeCopy .random pointers 
//     // yet.  Fill the nodes[] and nodeCopies[] arrays while traversing.  Assign new 
//     // sequential Node.val values into the input nodes, to be used as index values 
//     // into the nodes[] and nodeCopies[] arrays later, for creating the NodeCopy 
//     // .random links.
//     private NodeCopy traverse(Node node) {
//         if (node == null)  return null;
//         NodeCopy copy = new NodeCopy(node.val);
//         nodeCopies[nextNodeNum] = copy;
//         nodes[nextNodeNum] = node;
//         node.val = nextNodeNum++;
//         copy.left = traverse(node.left);
//         copy.right = traverse(node.right);
//         return copy;
//     }
// }