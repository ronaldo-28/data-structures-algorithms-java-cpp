class Solution {
    public int numSpecialEquivGroups(String[] w) {
    int n = w.length;
    Set<Integer> set = new HashSet<>();   
    for(int i=0; i<n; i++){
     set.add(abc(w[i]));
    }
    return set.size();
    }
    public int abc(String w){ 
      int even[] = new int[52];
      for(int i=0; i<w.length(); i++){
       char c = w.charAt(i);
       int idx = i%2==0?0:26; 
       even[c-'a'+idx]++;
    }
    return Arrays.hashCode(even);
    }
}