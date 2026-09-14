class Solution {
    public int maxDistance(String moves) {
        int counts[] = new int[128];

        for(int i=0;i<moves.length();i++){
            counts[moves.charAt(i)]++;
        }
        return Math.abs(counts['U'] - counts['D'])+Math.abs(counts['L']-counts['R'])+ counts['_'];
    }
}