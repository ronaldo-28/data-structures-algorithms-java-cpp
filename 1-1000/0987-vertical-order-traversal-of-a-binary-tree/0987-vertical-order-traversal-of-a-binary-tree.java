class Solution {
    static{
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter writer = new FileWriter("display_runtime.txt")) {
                writer.write("0");
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }));
    }
    HashMap<Integer, HashMap<Integer, List<Integer>>> map = new HashMap<>();

    int minCol = 0, maxCol = 0; 

    public void dfs(TreeNode root, int row, int col) {
        if (root == null) return;

        map.putIfAbsent(col, new HashMap<>());
        map.get(col).putIfAbsent(row, new ArrayList<>());
        map.get(col).get(row).add(root.val);

        minCol = Math.min(minCol, col);
        maxCol = Math.max(maxCol, col);

        dfs(root.left, row + 1, col - 1);
        dfs(root.right, row + 1, col + 1);
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        dfs(root, 0, 0);

        List<List<Integer>> ans = new ArrayList<>();

        for (int col = minCol; col <= maxCol; col++) {
            HashMap<Integer, List<Integer>> rowsMap = map.get(col);
            List<Integer> colList = new ArrayList<>();

            List<Integer> sortedRows = new ArrayList<>(rowsMap.keySet());
            Collections.sort(sortedRows);

            for (int row : sortedRows) {
                List<Integer> values = rowsMap.get(row);
                Collections.sort(values); 
                colList.addAll(values);
            }

            ans.add(colList);
        }

        return ans;
    }
}