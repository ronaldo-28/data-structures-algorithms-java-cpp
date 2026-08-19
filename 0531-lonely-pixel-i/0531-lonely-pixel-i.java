class Solution {
    public int findLonelyPixel(char[][] picture) {
        final int m = picture.length, n = picture[0].length;
        int[] cols = new int[n], rows = new int[m];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(picture[i][j] == 'B'){
                    rows[i]++;
                    cols[j]++;
                }
            }
        }

        int count = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(picture[i][j] == 'B'){
                    count += rows[i] == 1 && cols[j] == 1 ? 1 : 0;
                }
            }
        }
        return count;
    }
}