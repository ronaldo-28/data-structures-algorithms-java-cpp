class Solution {
    public List<List<String>> partition(String s) {
        return new java.util.AbstractList(){
            List<List<String>> result;
            private void init(){
                result = new ArrayList();
                backtrack(0, s, new ArrayList(), result);
            }

            @Override
            public int size(){
                if(result == null){
                    init();
                }
                return result.size();
            }

            @Override
            public List<String> get(int position){
                return result.get(position);
            }
        };
    }

    private void backtrack(int index, String s, List<String> list, List<List<String>> result){
        if(index == s.length()){
            // for(String item : list){
            //     if(!isPalindrome(item)){
            //         return;
            //     }
            // }
            result.add(new ArrayList(list));
            return;
        }
        for(int i = index; i < s.length(); i++){
            String st = s.substring(index, i + 1);
            if(isPalindrome(st)){
                list.add(st);
                backtrack(i + 1, s, list, result);
                list.remove(list.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s){
        int low = 0;
        int high = s.length() - 1;
        while(low <= high){
            if(s.charAt(low) != s.charAt(high)){
                return false;
            }
            low++;
            high--;
        }
        return true;
    }
}