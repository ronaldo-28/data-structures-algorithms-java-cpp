import java.util.*;
class Solution {
    public List<Double> averageOfLevels(TreeNode root) {

        List<Double> res = new AbstractList<>() {

            List<Double> result = null;

            @Override
            public int size() {
                if (result == null)
                    result = swapFunction(root);
                return result.size();
            }

            @Override
            public Double get(int index) {
                if (result == null)
                    result = swapFunction(root);
                return result.get(index);
            }
        };

        return res;
    }

    private List<Double> swapFunction(TreeNode root) {
        List<Double> result = new ArrayList<>();
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);

        while (!que.isEmpty()) {
            int size = que.size();
            double sum = 0.0;
            for (int i = 0; i < size; ++i) {
                TreeNode node = que.poll();
                sum += node.val;
                if (node.left != null)
                    que.add(node.left);
                if (node.right != null)
                    que.add(node.right);
            }
            result.add(sum / size);
        }

        return result;
    }
}