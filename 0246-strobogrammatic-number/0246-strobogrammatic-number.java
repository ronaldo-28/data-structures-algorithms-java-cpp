class Solution {
public boolean isStrobogrammatic(String num) {

        if (num == null || num.isEmpty()) {
            return false;
        }

        // 先將滿足頻閃數字的數字pair用HashMap存起來
        HashMap<Character, Character> hashMap = new HashMap<>();
        hashMap.put('0', '0');
        hashMap.put('1', '1');
        hashMap.put('8', '8');
        hashMap.put('6', '9');
        hashMap.put('9', '6');

        int left = 0;
        int right = num.length() - 1;

        // 前後雙指針方法
        // 本題當left = right的時候還是得做檢查, 因為本題不是真的回文, 所以最中間的字元還是得符合頻閃數字, 不能是隨意數字
        // 這邊是本題最大的陷阱, 自己練習的時候忽略了, 要非常小心
        while (left <= right) {

            // left有在hashMap的key裡面
            if (!hashMap.containsKey(num.charAt(left))) {
                return false;
            }

            // right必須是相對應的value
            if (hashMap.get(num.charAt(left)) != num.charAt(right)) {
                return false;
            }

            left ++;
            right --;

        }

        return true;
	
}
}