class Solution {
    public int closestTarget(String[] words, String target, int startIndex) {

        int n = words.length;
        for (int i = 0; i < n; i++)
            if (words[(startIndex + i + n) % n].equals(target))
                return i;
            else if (words[(startIndex - i + n) % n].equals(target))
                return i;

        return -1;
    }
}