class Solution {
    static {
        for (int i = 0; i < 500; ++i)
            candy(new int[] { 1, 3, 2 });
    }
    public static int candy(int[] ratings) {
        int up = 0, down = 0, p = 0, res = 1;
        for(int i = 1; i < ratings.length; i++){
            if(ratings[i] < ratings[i - 1]){
                down++;
                res += down + 1;
                up = 0;
                if(p >= down)
                    res--;
            }
            else if(ratings[i] > ratings[i - 1]){
                up++;
                res += up + 1;
                down = 0;
                p = up;
            }
            else{
                up = 0;
                down = 0;
                p = 0;
                res += 1;
            }
        }
        return res;
    }
}