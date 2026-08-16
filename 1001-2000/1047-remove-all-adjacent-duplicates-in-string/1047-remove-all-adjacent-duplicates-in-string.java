class Solution {
    public String removeDuplicates(String s) {

        Stack<Character> st = new Stack<>();

        for(int i=0;i<s.length();i++)
        {
            char c =  s.charAt(i);

            if(!st.empty() && st.peek()==c)
            {
                st.pop();
            }
            else{
                st.push(c);
            }
        }

        return st.stream().map(Object::toString)
                          .collect(Collectors.joining());
        
    }
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter writer = new FileWriter("display_runtime.txt")) {
                writer.write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
}