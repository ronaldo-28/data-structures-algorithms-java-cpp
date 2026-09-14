class Solution {
    private static final String[] hasPath = {
        ".............",
        "#............",
        "##...........",
        "###..........",
        "####.........",
        "#####........",
        "######.......",
        "#######......",
        "########.....",
        "#########...."
    }, noPath = {
        "..##########.",
        "#..#########.",
        "##..########.",
        "###..#######.",
        "####..######.",
        "#####..#####.",
        "######..####.",
        "#######..###.",
        "########..##.",
        "#########..#."
    };

    public List<String> createGrid(int k) {
        int msb = 9;
        while((k & 1 << msb) == 0) msb--;

        String[] ans = new String[msb + 1 << 1];
        for(int i = 0; i <= msb; i++) {
            ans[i << 1] = (k & 1 << i) == 0 ? noPath[i] : hasPath[i];
            ans[i << 1 | 1] = noPath[i];
        }

        return List.of(ans);
    }
}