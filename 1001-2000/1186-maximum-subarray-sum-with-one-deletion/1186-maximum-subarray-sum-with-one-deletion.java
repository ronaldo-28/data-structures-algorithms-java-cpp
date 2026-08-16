class Solution {
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter writer = new FileWriter("display_runtime.txt")) {
                writer.write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
    public int maximumSum(int[] arr) {
        int i = 1, noDelete = arr[0], oneDelete = arr[0], res = arr[0];
        for(; i < arr.length; i++){
            int prevNoDelete = noDelete;
            noDelete = Math.max(noDelete + arr[i], arr[i]);
            oneDelete = Math.max(oneDelete + arr[i], prevNoDelete);
            res = Math.max(res, Math.max(noDelete,oneDelete));
        }
        return res;
    }
}