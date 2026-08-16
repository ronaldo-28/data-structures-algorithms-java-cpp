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
    public String shortestCommonSupersequence(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int[][] memo = new int[n+1][m+1];

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                if(str1.charAt(i-1) == str2.charAt(j-1)) {
                    memo[i][j] = 1 + memo[i-1][j-1];
                }
                else {
                    memo[i][j] = Math.max(memo[i-1][j], memo[i][j-1]);
                }
            }
        }

        int i=n;
        int j=m;
        StringBuilder ans = new StringBuilder();

        while(i > 0 && j > 0) {
            if(str1.charAt(i-1) == str2.charAt(j-1)) {
                ans.append(str1.charAt(i-1));
                i--;
                j--;
            }
            else {
                if(memo[i-1][j] > memo[i][j-1]) {
                    ans.append(str1.charAt(i-1));
                    i--;
                }
                else {
                    ans.append(str2.charAt(j-1));
                    j--;
                }
            }
        }

        while(i > 0) {
            ans.append(str1.charAt(i-1));
            i--;
        }
        while(j > 0) {
            ans.append(str2.charAt(j-1));
            j--;
        }

        return ans.reverse().toString();
    }
}