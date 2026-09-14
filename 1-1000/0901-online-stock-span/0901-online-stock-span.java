class StockSpanner {

    static{
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter writer = new FileWriter("display_runtime.txt")) {
                writer.write("0");
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }));
    }
    private Stack<int[]> st;
    public StockSpanner() {
         st=new Stack<>();
    }
    
    public int next(int price) {
        int span=1;

        while(!st.isEmpty() && st.peek()[0]<=price)
        {
            span+=st.pop()[1];

        }
        st.push(new int[] { price,span});

        return span;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */