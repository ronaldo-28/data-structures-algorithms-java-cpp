import java.util.*;

class AugmentedAVLTree {
    private class Node {
        int value;
        int xor;
        int size;
        int priority;
        boolean reversed;
        Node left, right;
        
        Node(int value) {
            this.value = value;
            this.xor = value;
            this.size = 1;
            this.priority = new Random().nextInt();
            this.reversed = false;
        }
    }
    
    private Node root;
    
    private int size(Node node) {
        return node == null ? 0 : node.size;
    }
    
    private int xor(Node node) {
        return node == null ? 0 : node.xor;
    }
    
    private void pushDown(Node node) {
        if (node != null && node.reversed) {
            node.reversed = false;
            Node temp = node.left;
            node.left = node.right;
            node.right = temp;
            
            if (node.left != null) node.left.reversed ^= true;
            if (node.right != null) node.right.reversed ^= true;
            
            // Recalculate XOR if children were swapped
            node.xor = node.value ^ xor(node.left) ^ xor(node.right);
        }
    }
    
    private void update(Node node) {
        if (node != null) {
            node.size = 1 + size(node.left) + size(node.right);
            node.xor = node.value ^ xor(node.left) ^ xor(node.right);
        }
    }
    
    private Node[] split(Node node, int key) {
        if (node == null) return new Node[]{null, null};
        
        pushDown(node);
        Node[] result;
        int currentKey = size(node.left);
        
        if (key <= currentKey) {
            result = split(node.left, key);
            node.left = result[1];
            result[1] = node;
        } else {
            result = split(node.right, key - currentKey - 1);
            node.right = result[0];
            result[0] = node;
        }
        
        update(node);
        return result;
    }
    
    private Node merge(Node left, Node right) {
        if (left == null) return right;
        if (right == null) return left;
        
        pushDown(left);
        pushDown(right);
        
        if (left.priority > right.priority) {
            left.right = merge(left.right, right);
            update(left);
            return left;
        } else {
            right.left = merge(left, right.left);
            update(right);
            return right;
        }
    }
    
    public void insert(int index, int value) {
        Node[] parts = split(root, index);
        root = merge(merge(parts[0], new Node(value)), parts[1]);
    }
    
    public void update(int index, int value) {
        Node[] parts1 = split(root, index);
        Node[] parts2 = split(parts1[1], 1);
        parts2[0].value = value;
        update(parts2[0]);
        root = merge(parts1[0], merge(parts2[0], parts2[1]));
    }
    
    public int rangeXOR(int left, int right) {
        Node[] parts1 = split(root, left);
        Node[] parts2 = split(parts1[1], right - left + 1);
        int result = xor(parts2[0]);
        root = merge(parts1[0], merge(parts2[0], parts2[1]));
        return result;
    }
    
    public void reverse(int left, int right) {
        Node[] parts1 = split(root, left);
        Node[] parts2 = split(parts1[1], right - left + 1);
        parts2[0].reversed ^= true;
        root = merge(parts1[0], merge(parts2[0], parts2[1]));
    }
    
    // Helper method to build initial tree
    public void build(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            insert(i, nums[i]);
        }
    }
}

class Solution {
    public int[] getResults(int[] nums, int[][] queries) {
        AugmentedAVLTree tree = new AugmentedAVLTree();
        tree.build(nums);
        List<Integer> results = new ArrayList<>();
        
        for (int[] query : queries) {
            int type = query[0];
            if (type == 1) {
                // Update
                int index = query[1];
                int value = query[2];
                tree.update(index, value);
            } else if (type == 2) {
                // Range XOR
                int left = query[1];
                int right = query[2];
                results.add(tree.rangeXOR(left, right));
            } else if (type == 3) {
                // Reverse
                int left = query[1];
                int right = query[2];
                tree.reverse(left, right);
            }
        }
        
        return results.stream().mapToInt(i -> i).toArray();
    }
}