class Solution {
    public boolean isvowel(char ch){
        return ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u';
    }
    public String sortVowels(String s) {
        Map<Character,int[]> map=new HashMap<>();
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(isvowel(ch)){
            map.putIfAbsent(ch,new int[]{0,i});
            map.get(ch)[0]++;
        }
        }
        PriorityQueue<Character> queue =new PriorityQueue<>((a,b)-> {
            int freq1[]=map.get(a);
            int freq2[]=map.get(b);
            if(freq1[0]!=freq2[0]){
                return freq2[0]-freq1[0];
        }
            else{
                return freq1[1]-freq2[1];
            }});
        queue.addAll(map.keySet());
        List<Character> list=new ArrayList<>();
        while(!queue.isEmpty()){
            char ch=queue.poll();
            int count=map.get(ch)[0];
            for(int i=0;i<count;i++){
                list.add(ch);
            }
        }
        StringBuilder stb=new StringBuilder();
        int i=0;
        for(char ch:s.toCharArray()){
        if(isvowel(ch)){
            stb.append(list.get(i++));
        }
            else{
                stb.append(ch);
            }
    }
        return stb.toString();
    }
}