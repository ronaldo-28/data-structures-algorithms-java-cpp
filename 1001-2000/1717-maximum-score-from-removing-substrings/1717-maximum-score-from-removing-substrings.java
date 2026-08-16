class Solution {
    public int maximumGain(String s, int x, int y) {
        int res = 0;
        char[] letters = s.toCharArray();
        int n = letters.length;
        char a = 'a', b = 'b';
        int aCnt = 0, bCnt = 0;
        if (x < y) {
            int tmp = x;
            x = y;
            y = tmp;
            a = 'b';
            b = 'a';
        }
        for (int i = 0; i < n; i++) {
            if (letters[i] == a) {
                aCnt++;
            } else if (letters[i] == b) {
                if (aCnt > 0) {
                    aCnt--;
                    res += x;
                } else {
                    bCnt++;
                }
            } else {
                res += Math.min(aCnt, bCnt) * y;
                aCnt = 0;
                bCnt = 0;
            }
        }
        if (aCnt!= 0) {
            res += Math.min(aCnt, bCnt) * y;
        }

        return res;
    }

/*
    public int maximumGain(String s, int x, int y) {

        int res = 0;
        char[] letters = s.toCharArray();
        Deque<Character> stack = new LinkedList<>();
        int[] scores = new int[2];
        List<BiPredicate<Character, Character>> checks = new LinkedList<>();
        if (x > y) {
            checks.add(Solution::checkAb);
            checks.add(Solution::checkBa);
            scores[0] = x;
            scores[1] = y;
        } else {
            checks.add(Solution::checkBa);
            checks.add(Solution::checkAb);
            scores[0] = y;
            scores[1] = x;
        }
        boolean[] hasOccurence = new boolean[2];
        int idx = 0;
        BiPredicate<Character, Character> checker = checks.get(idx);
        stack.offer(letters[0]);
        int lettersLength = letters.length;
        for (int i = 1; i < lettersLength; i++) {
            char c = letters[i];
            if (!stack.isEmpty() && checker.test(stack.peekLast(), c)) {
                stack.removeLast();
                res += scores[idx];
                hasOccurence[0] = true;
            } else {
                stack.offer(c);
            }
        }
        idx++;

        hasOccurence[idx] = false;
        checker = checks.get(idx);
        if (stack.isEmpty()) {
            return res;
        }
        int size = stack.size();
        stack.offer(stack.pollFirst());
        for (int i = 1; i < size && !stack.isEmpty(); i++) {
            char c = stack.pollFirst();
            if (!stack.isEmpty() && checker.test(stack.peekLast(), c)) {
                stack.removeLast();
                res += scores[idx];
                hasOccurence[idx] = true;
            } else {
                stack.offer(c);
            }
        }

        return res;
    }

    static boolean checkAb(Character a, Character b) {
        return a == 'a' && b == 'b';
    }

    static boolean checkBa(Character b, Character a) {
        return b == 'b' && a == 'a';
    }
*/
}