class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int i = 0;
        int len = flowerbed.length;

        // n이 0보다 클 때만 돕니다. (다 심었으면 즉시 종료)
        while (i < len && n > 0) {
            
            // 1. 현재 자리에 이미 꽃(1)이 있는 경우
            if (flowerbed[i] == 1) {
                // 다음 칸은 인접해서 절대 못 심음 -> 2칸 점프
                i += 2;
            }
            // 2. 현재가 0이고, '오른쪽'도 비었거나 끝인 경우
            // (왼쪽은 점프 로직 덕분에 이미 비어있음이 보장됨)
            else if (i == len - 1 || flowerbed[i + 1] == 0) {
                n--;
                // 여기에 심었으니 다음 칸은 못 심음 -> 2칸 점프
                i += 2;
            }
            // 3. 현재가 0인데 오른쪽이 1인 경우 (패턴: 0, 1, ...)
            else {
                // 현재(0)는 오른쪽 꽃 때문에 못 심음
                // 다음(1)은 꽃이 있어서 못 심음
                // 다다음(1의 오른쪽)은 꽃 옆이라 못 심음
                // -> 총 3칸을 건너뛸 수 있음!
                i += 3;
            }
        }
        
        return n <= 0;
    }
}