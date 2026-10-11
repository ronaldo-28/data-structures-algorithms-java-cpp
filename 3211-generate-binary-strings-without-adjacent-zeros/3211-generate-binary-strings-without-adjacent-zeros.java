import java.util.AbstractList; 
class Solution {
    private List<String> list;
    public List<String> validStrings(int n) {
        return new AbstractList<String>(){
            @Override
            public int size(){
                init();
                return list.size();
            }
            @Override
            public String get(int i){
                init();
                return list.get(i);
            }
            private void init(){
                if (list != null) return;
                list = new ArrayList<>();
                generator(n, "");
            }
        };
    
    }
    private void generator(int n, String s){
        if (s.length() == n){
            list.add(s);
            return;
        }
        generator(n, s+"1");
        if (s.length() != 0 && s.charAt(s.length()-1) != '0') generator(n, s + "0");
        if (s.length() == 0) generator(n, s+"0");
    }
}