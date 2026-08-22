class Solution {
    public boolean areSentencesSimilar(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        

        if(sentence1.length!=sentence2.length) return false;

        for(int i=0;i<sentence1.length;i++){

            if(!sentence1[i].equals(sentence2[i])){

                boolean matched = false;
                 
                for(List<String> st:similarPairs){

                    if(st.contains(sentence1[i])){

                        if(!st.get(1).equals(sentence2[i]) && !st.get(0).equals(sentence2[i])){

                            matched = false;
                        }
                        else{
                            matched=true;
                            break;
                        }
                    }
                }

                if(!matched){

                    return false;
                }

            }
        }

        return true;
    }
}