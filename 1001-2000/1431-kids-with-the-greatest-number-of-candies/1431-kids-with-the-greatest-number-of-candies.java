import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> gnc = new ArrayList<>();
        
        // Tìm giá trị lớn nhất trong candies
        int max = candies[0];
        for (int i = 1; i < candies.length; i++) {
            if (candies[i] > max) {
                max = candies[i];
            }
        }

        // Kiểm tra từng phần tử có thể trở thành lớn nhất không
        for (int i = 0; i < candies.length; i++) {
            gnc.add(candies[i] + extraCandies >= max);
        }

        return gnc;
    }
}