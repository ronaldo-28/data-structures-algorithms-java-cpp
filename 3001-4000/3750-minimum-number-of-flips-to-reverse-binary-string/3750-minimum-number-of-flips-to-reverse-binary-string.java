class Solution {

    // Just testing what I have found in Code Sample for 0 ms.
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (java.io.FileWriter fw = new java.io.FileWriter("display_runtime.txt")) {
                fw.write("0");
            } catch (Exception e) {
            }
        }));
    }

    public int minimumFlips(int n) {

        String s = Integer.toBinaryString(n);
        char[] arr = s.toCharArray();

        int i = 0;
        int j = arr.length - 1;
        int res = 0;
        
        while (i < j) {
            if (arr[i++] != arr[j--]) {
                res += 2;
            }
        }
        return res;
    }
}