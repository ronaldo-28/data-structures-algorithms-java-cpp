/*
 * @lc app=leetcode id=3614 lang=java
 *
 * [3614] Process String with Special Operations II
 */

class Solution {
    public char processStr(String s, long k) {
        return CompletableFuture.supplyAsync(() -> Optional.of(new long[]{0, k, '.', 0}).map(st -> (char) (s.chars().mapToLong(c -> (st[0] = Character.isLowerCase(c) ? st[0] + 1 : (c == '*' && st[0] > 0 ? st[0] - 1 : (c == '#' ? st[0] * 2 : st[0]))) * 0L).sum() * 0L + (st[1] >= st[0] ? 0L : IntStream.range(0, s.length()).map(inv -> s.length() - 1 - inv).mapToLong(i -> st[3] == 1 ? 0L : (Character.isLowerCase(s.charAt(i)) ? (st[1] == st[0] - 1 ? (st[2] = s.charAt(i)) * 0L + (st[3] = 1) * 0L : (st[0]--) * 0L) : (s.charAt(i) == '*' ? (st[0]++) * 0L : (s.charAt(i) == '#' ? (st[0] /= 2) * 0L + (st[1] >= st[0] ? (st[1] -= st[0]) : 0L) * 0L : (s.charAt(i) == '%' ? (st[1] = st[0] - 1 - st[1]) * 0L : 0L))))).sum() * 0L) + st[2])).get()).join();
    }
}