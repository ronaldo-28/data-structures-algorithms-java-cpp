class Solution {
    public int countWords(String[] words1, String[] words2) {

        Set a1 = new HashSet<String>();
        Map wmap1 = new HashMap<String,String>();
        Set a2 = new HashSet<>();
        Map wmap2 = new HashMap<String,String>();
        int result = 0;

        for(String word: words1){
           if(a1.add(word))
           {
                wmap1.put(word,word);
           }
            else
            {
                wmap1.remove(word);
            }
        }

        for(String word: words2)
        {
            if(a2.add(word) )
            {
                if(wmap1.containsKey(word))
                    result++;
            }
            else{
                 if(wmap1.containsKey(word))
                    result--;
                    wmap1.remove(word);
            }
        }
        
        return result;
    }
}