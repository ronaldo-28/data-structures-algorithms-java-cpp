class Solution {
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int x = -3;
        if(ruleKey.equals("type")){
           x= check(items,0,ruleValue);
        }
         if(ruleKey.equals("color")){
           x= check(items,1,ruleValue);
        }
         if(ruleKey.equals("name")){
           x= check(items,2,ruleValue);
        }
        return x;
    }
    public static int check(List<List<String>> items,int i,String ruleValue){
       int n = items.size(); int c = 0;
       for(int j =0;j<n;j++){
        String s =items.get(j).get(i);
        if(s.equals(ruleValue)){
          c++;
        }
       }
       return c;
    }
}