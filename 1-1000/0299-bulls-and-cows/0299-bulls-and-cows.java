class Solution {
    public String getHint(String secret, String guess) {
        int secdigs[] = new int[10];
        int guessdigs[] = new int[10];
        int bull = 0, cow = 0;

        for(int i = 0; i < secret.length() ; i ++) {
            if(secret.charAt(i) == guess.charAt(i)) {
                bull ++;
            }
            else {
                secdigs[secret.charAt(i) - '0'] ++;
                guessdigs[guess.charAt(i) - '0'] ++;
            }
        }
        for(int i = 0; i < 10; i ++) {
            int minVal = secdigs[i];
            if(minVal > guessdigs[i]) minVal = guessdigs[i];
            cow += minVal;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(bull).append("A").append(cow).append("B");
        return sb.toString();
    }
}