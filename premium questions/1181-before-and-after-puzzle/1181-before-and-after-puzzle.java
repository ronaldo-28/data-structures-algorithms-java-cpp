class Solution {
	public List<String> beforeAndAfterPuzzles(String[] phrases) {
		List<String> res=new ArrayList<>();
		HashMap<String,List<Phrases>> map=new LinkedHashMap<>();
		for(int i=0;i<phrases.length;i++){
			String[] st=phrases[i].split(" ");
			List<Phrases> l=map.getOrDefault(st[0],new ArrayList<Phrases>());
			l.add(new Phrases(phrases[i],i));
			map.put(st[0],l);
		}

		Set<String> setSt=new HashSet<>();

		for(int i=0;i<phrases.length;i++){
			String[] st=phrases[i].split(" ");
			if(map.containsKey(st[st.length-1])){
				for(Phrases ph:map.get(st[st.length-1])){
					if(i!=ph.getIndex()){
						st[st.length-1]="";
						String s=String.join(" ",st);
						setSt.add(s+""+ph.getPhrase());
					}
				}
			}
		}

		res.addAll(setSt);
		Collections.sort(res);
		return res;
	}

	class Phrases{
		String phrase;
		int index;

		Phrases(String phrase,int index){
			this.phrase=phrase;
			this.index=index;
		}

		public String getPhrase(){
			return phrase;
		}

		public int getIndex(){
			return index;
		}
	}
}