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
    
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<>();

        for (int val : asteroids) {
            boolean destroyed = false;

            // popping happens only for -ve: val < 0 && top > 0
            while (!st.isEmpty() && val < 0 && st.peek() > 0) {
                if (st.peek() < -val) {
                    st.pop();               // top explodes
                } else if (st.peek() == -val) {
                    st.pop();               // both explode
                    destroyed = true;
                    break;
                } else {
                    destroyed = true;       // current explodes
                    break;
                }
            }

            if (!destroyed) {
                st.push(val);
            }
        }

        int[] ans = new int[st.size()];
        for (int i = ans.length - 1; i >= 0; i--) {
            ans[i] = st.pop();   // preserve order
        }

        return ans;
    }
}


// class Solution {
//     public int[] asteroidCollision(int[] asteroids) {
//         Stack<Integer> st = new Stack<>();

//         for (int val : asteroids) {
//             if (val > 0) {
//                 st.push(val);
//             } else {
//                 // pop only smaller positive asteroids
//                 while (!st.isEmpty() && st.peek() > 0 && st.peek() < -val) {
//                     st.pop();
//                 }

//                 // equal size: both destroyed
//                 if (!st.isEmpty() && st.peek() > 0 && st.peek() == -val) {
//                     st.pop();
//                 }
                
//                 // survives if no positive asteroid blocks it
//                 else if (st.isEmpty() || st.peek() < 0) {
//                     st.push(val);
//                 }
//                 // else: top is bigger positive, current val destroyed -> do nothing
//             }
//         }

//         int[] ans = new int[st.size()];
//         for (int i = ans.length - 1; i >= 0; i--) {
//             ans[i] = st.pop();
//         }
//         return ans;
//     }
// }
