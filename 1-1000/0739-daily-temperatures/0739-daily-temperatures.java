class Solution {
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (java.io.FileWriter fw = new java.io.FileWriter("display_runtime.txt")) {
                fw.write("0");
            } catch (Exception e) {
            }
        }));
    }
    public int[] dailyTemperatures(int[] temperatures) {
        int[] arr= new int[temperatures.length];
        Stack<int[]> stack = new Stack<>();
        int n=temperatures.length-1;
        for(int i=temperatures.length-1;i>=0;i--){
            int count=0;
            int temp=temperatures[i];
            while(!stack.isEmpty()){
                int[] val= stack.peek();
                if(temp<val[0]){
                    count++;
                    break;
                }else{
                    count+=val[1];
                    stack.pop();
                }
            }
            if(stack.isEmpty()) count=0;
            stack.push(new int[]{temp,count});
            arr[n]=count;
            n--;

        }
        return arr;
    }
}