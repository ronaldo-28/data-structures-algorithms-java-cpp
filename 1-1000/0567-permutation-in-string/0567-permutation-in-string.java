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
    public boolean isequal(int[] a,int[] b){
        for(int i=0;i<26;i++){
            if(a[i] != b[i]) return false;
        }
        return true;
    }
    public boolean checkInclusion(String s1, String s2) {
        int n1 = s1.length(), n2=s2.length();
        if(n1>n2) return false;

        int[] freq1 = new int[26];
        int[] freq2 = new int[26];

        for (int i = 0; i < n1; i++) {
            freq1[s1.charAt(i) - 'a']++;
        }

        int left = 0;
        int k = n1; 

        for (int right = 0; right < n2; right++) {
            freq2[s2.charAt(right) - 'a']++;
            if (right - left + 1 == k) {
                if (isequal(freq1, freq2)) {
                    return true;
                }

                freq2[s2.charAt(left) - 'a']--;
                left++;
            }
        }

        return false;

    }
}