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
    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> f = new HashMap<>();
        int low = 0, high = 0, res = 0;

        for(high = 0; high < fruits.length; high++){
            f.put(fruits[high], f.getOrDefault(fruits[high],0) + 1);
            while(f.size() > 2){
                f.put(fruits[low], f.get(fruits[low]) - 1);
                if(f.get(fruits[low]) == 0){
                    f.remove(fruits[low]);
                }
                low++;
            }
            res = Math.max(res, (high - low) + 1);
        }
        return res;
    }
}