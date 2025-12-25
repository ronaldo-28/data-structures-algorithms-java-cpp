import java.util.AbstractList;
class Solution {
    private List<String> _topKeysLs = null;

    public List<String> topKFrequent(String[] words, int k) {
        final Map<String,Integer> _freqMap = new HashMap<String,Integer>();
        final List<String> _keysLs = new ArrayList<String>();
        return new AbstractList<String>() {
            public String get(int index) {
                init(); return _topKeysLs.get(index);
            }
            public int size() {
                init(); return _topKeysLs.size();
            }
            protected void init() {
                if (_topKeysLs != null) return;

                for (String w: words) _freqMap.put(w, _freqMap.getOrDefault(w, 0) + 1);
                _keysLs.addAll(_freqMap.keySet());
                Comparator<String> comparator = (String a, String b) -> {
                    int freq1 = _freqMap.get(a), freq2 = _freqMap.get(b);

                    if (freq1 < freq2) return 1;
                    else if(freq1 > freq2) return -1;
                    else return a.compareTo(b); 
                };
                Collections.sort(_keysLs, comparator);
                _topKeysLs = _keysLs.subList(0, k);
            }
        };
    }
}