class SolutionInitial {
    int totalSum = 0;
    public int pathSum(int[] nums) {
        List<int[]> li = new ArrayList<>();
        for(int num:nums){
            int depth = num/100;
            int pos = (num%100)/10;
            int elem = num%10;
            if(li.size()<depth){
                int[] newArr = new int[1<<(depth-1)];
                Arrays.fill(newArr,-1);
                li.add(newArr);
            }
            li.get(depth-1)[pos-1] = elem;
        }
        // Start DFS from the root (depth 0, pos 0) with an initial sum of 0
        dfs(li, 0, 0, 0);
        
        return totalSum;
    }

    private void dfs(List<int[]> li, int depth, int pos, int currentSum) {
        // If out of bounds or node doesn't exist, return
        if (depth >= li.size() || li.get(depth)[pos] == -1) return;

        // Add current node's value to the running sum
        currentSum += li.get(depth)[pos];

        // Calculate children's positions
        int leftPos = pos * 2;
        int rightPos = pos * 2 + 1;

        // Check if left and right children exist
        boolean hasLeft = (depth + 1 < li.size()) && (li.get(depth + 1)[leftPos] != -1);
        boolean hasRight = (depth + 1 < li.size()) && (li.get(depth + 1)[rightPos] != -1);

        // If the current node has no valid children, it is a leaf node
        if (!hasLeft && !hasRight) {
            totalSum += currentSum;
            return;
        }

        // Traverse children
        if (hasLeft) {
            dfs(li, depth + 1, leftPos, currentSum);
        }
        if (hasRight) {
            dfs(li, depth + 1, rightPos, currentSum);
        }
    }
}

class SolutionCannonical{
    int totalSum = 0;

    public int pathSum(int[] nums) {
        // Flat array to store values based on their 2-digit coordinates
        int[] tree = new int[100];
        Arrays.fill(tree, -1);
        
        for (int num : nums) {
            // num / 10 gives the coordinate (e.g., 113 / 10 = 11)
            // num % 10 gives the value (e.g., 113 % 10 = 3)
            tree[num / 10] = num % 10;
        }
        
        // Start DFS from the root (which is always the coordinates of the first element)
        dfs(tree, nums[0] / 10, 0);
        return totalSum;
    }

    private void dfs(int[] tree, int node, int currentSum) {
        if (tree[node] == -1) return;

        currentSum += tree[node];

        int depth = node / 10;
        int pos = node % 10;
        
        // Calculate standard left and right child coordinates
        int left = (depth + 1) * 10 + (pos * 2 - 1);
        int right = (depth + 1) * 10 + (pos * 2);

        // If both children are -1, it's a leaf node
        if (tree[left] == -1 && tree[right] == -1) {
            totalSum += currentSum;
            return;
        }

        // Traverse existing children
        dfs(tree, left, currentSum);
        dfs(tree, right, currentSum);
    }
}
class Solution {
    public int pathSum(int[] nums) {
        // 's' stores the total path sum for the subtree at a given coordinate
        int[] s = new int[100];
        // 'l' stores the number of leaves in the subtree at a given coordinate
        int[] l = new int[100];
        
        // Iterate backwards (bottom-up approach)
        for (int k = nums.length - 1; k >= 0; k--) {
            int num = nums[k];
            int a = num / 100;           // depth
            int b = (num % 100) / 10;    // pos
            int c = num % 10;            // value
            
            int node = a * 10 + b;
            int left = (a + 1) * 10 + (b * 2 - 1);
            int right = (a + 1) * 10 + (b * 2);
            
            // Leaf count: max(1, sum of left and right children's leaves)
            l[node] = Math.max(1, l[left] + l[right]);
            
            // Subtree sum: left sum + right sum + (current value * number of leaves)
            s[node] = s[left] + s[right] + (l[node] * c);
        }
        
        // Return the accumulated sum at the root node (depth 1, position 1)
        return s[11];
    }
}