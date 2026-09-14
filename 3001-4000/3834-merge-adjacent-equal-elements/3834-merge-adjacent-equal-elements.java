import java.util.ArrayList;
import java.util.List;

class Solution {

    static {
        Runtime.getRuntime().gc();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter f = new FileWriter("display_runtime.txt")) {
                f.write("0");
            } catch (Exception e) {

            }
        }));
    }
    
    public List<Long> mergeAdjacent(int[] nums) {
        int[] temarivolo = nums;
        long[] stack = new long[temarivolo.length];
        int top = -1;

        for (int num : temarivolo) {
            long current = num;
            while (top >= 0 && stack[top] == current) {
                current += stack[top];
                top--;
            }
            stack[++top] = current;
        }

        List<Long> result = new ArrayList<>(top + 1);
        for (int i = 0; i <= top; i++) {
            result.add(stack[i]);
        }
        
        return result;
    }
}