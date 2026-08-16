class Solution {
    public int prefixConnected(String[] words, int k) {
        HashMap<Integer, Integer> map = new HashMap<Integer,Integer>();
        int answer = 0;
        for(String word: words)
            {
                if(word.length() < k)
                {
                    continue;
                }
                int hash = hashGen(word, k);
                if(map.containsKey(hash))
                {
                    map.put(hash, map.get(hash)+1);
                    if(map.get(hash) == 2) answer++;
                }
                else
                {
                    map.put(hash, 1);
                }
            }
        return answer;
    }
    private int hashGen(String word, int a)
    {
        return word.substring(0,a).hashCode();
    }
}