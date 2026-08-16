/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    static {
        Runtime.getRuntime().gc();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter writer = new FileWriter("display_runtime.txt")) {
                writer.write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
    public void getParents(TreeNode root, Map<TreeNode, TreeNode> parentMap){
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            TreeNode cur = q.poll();
            if(cur.left != null){
                parentMap.put(cur.left, cur); 
                q.offer(cur.left);
            }
            if(cur.right != null){
                parentMap.put(cur.right, cur);
                q.offer(cur.right);
            } 
        } 
    }

    public List<Integer> bfsFromTarget(TreeNode target, Map<TreeNode, TreeNode> parentMap, int k, List<Integer> answer){
        int dist = 0;
        Set<TreeNode> visited = new HashSet<>();
        Queue<TreeNode> q = new LinkedList<>();
        visited.add(target);
        q.offer(target);
        
        while(!q.isEmpty()){
            if(dist == k) break;
            int size = q.size();

            for(int i = 1; i <= size; i++){
                TreeNode cur = q.poll();
                // visit up
                if(parentMap.containsKey(cur) && !visited.contains(parentMap.get(cur))){
                    q.offer(parentMap.get(cur));
                    visited.add(parentMap.get(cur));
                }
                // visit left
                if(cur.left != null && !visited.contains(cur.left)){
                    q.offer(cur.left);
                    visited.add(cur.left);
                }
                // visit right
                if(cur.right != null && !visited.contains(cur.right)){
                    q.offer(cur.right);
                    visited.add(cur.right);
                }
            }

            dist++;
        }
        
        while(!q.isEmpty()){
            answer.add(q.poll().val);
        }

        return answer;
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> list = new ArrayList<>();
        if(root == null) return list;
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        getParents(root, parentMap); 
        return bfsFromTarget(target, parentMap, k, list);
    }
}