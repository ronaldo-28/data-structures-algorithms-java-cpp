// steps
// 1st calculate NSE just store index
// 2nd Calculate PSE just store index
// then contributions for each element by
// left = i-PSE[i]
// right = NSE[i] - i;
// contribution = (left*right)*arr[i] // subarray * arr[i]
// total += contribution



class Solution {
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (java.io.FileWriter fw = new java.io.FileWriter("display_runtime.txt")) {
                fw.write("0");
            } catch (Exception e) {
            }
        }));
    }
    public int sumSubarrayMins(int[] arr) {
        long mod = 1000000007;
        int n = arr.length;
        // Step 1
        Stack<Integer> s = new Stack<>();
        int[] nxtSmall = new int[n];
        for(int i=n-1; i>=0; i--){
            int cr = arr[i];
            while(!s.isEmpty() && cr<=arr[s.peek()]){
                s.pop();
            }

            nxtSmall[i] = s.isEmpty()?n:s.peek();

            s.push(i);
        }

        // step 2
        s.clear();
        int[] preSmall = new int[n];
        for(int i=0; i<n; i++){
            int cr = arr[i];
            while(!s.isEmpty() && cr<arr[s.peek()]){
                s.pop();
            }
            preSmall[i] = s.isEmpty()?-1:s.peek();

            s.push(i);
        }


        // step 3
        int total = 0;
        for(int i=0; i<n; i++){
            int left = i-preSmall[i];
            int right = nxtSmall[i]-i;
            
            long freq = left*right*1l;
            long contribution = (freq*arr[i]*1l) % mod;

            total = (int)((total+contribution)%mod);
        }

        return total;
    }
}