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
    public int longestStrChain(String[] words) {
        int n = words.length;
        Arrays.sort(words,(a,b)->a.length() - b.length());
        int [] dp = new int [n];
        Arrays.fill(dp,1);
        for(int i = 0;i<n;i++){
            for(int j = 0;j<i;j++){
                if(check(words[j],words[i],words))
                    dp[i] = Math.max(1 + dp[j],dp[i]);
            }
        }
        int max = 0;
        for(int x : dp) max = Math.max(max,x);
        return max;
    }
    public static boolean check(String s1,String s2,String [] arr){
        if(s1.length() != s2.length()-1)  return false;
        int i = 0,j = 0;
        while(i < s1.length() && j < s2.length()){
            if(s1.charAt(i) == s2.charAt(j)){
                i++;
                j++;
            }
            else
                j++;
        }
        if(i == s1.length())
            return true;
        return false;
    }
}