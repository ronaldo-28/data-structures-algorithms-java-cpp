class Solution {
    public int minLength(String s, int numOps) {
		if(s == null || s.isEmpty()) {
			return 0;
		}

		if(this.minLength1(s, numOps)) {
			return 1;
		}
		
		List<Interval> intervals = this.segregate(s);
		
		int lower = 1;		
		
		int max = 0;
		for(var intv : intervals) {
			if(intv.end() - intv.begin() > max) {
				max = intv.end() - intv.begin();
			}
		}
		
		int upper = max;
		
		while(upper - lower > 1) {
			int mid = (upper + lower) / 2;
			
			if(this.minLength(intervals, numOps, mid)) {
				upper = mid;
			}else {
				lower = mid;
			}
		}
		
		return upper;
	}
	
	protected boolean minLength(List<Interval> intervals, int numOps, int minLen) {
		int used = 0;
		
		for(var intv : intervals) {
			int len = intv.end() - intv.begin();
			if(len <= minLen) {
				continue;
			}
			
			int req = len / (minLen + 1);
			if(used + req > numOps) {
				return false;
			}
			
			used += req;
		}
		
		return true;
	}
	
	protected List<Interval> segregate(String s) {
		int len = s.length();
		
		List<Interval> intervals = new ArrayList<>();
		
		char prev = '\0';
		int begin = 0;
		
		for(int i = 0; i < len; i++) {
			char ch = s.charAt(i);
			if(ch == prev) {
				continue;
			}
			
			if(i - begin > 1) {
				intervals.add(new Interval(begin, i));
			}
				
			prev = ch;
			begin = i;
		}
		
		if(len - begin > 1) {
			intervals.add(new Interval(begin, len));
		}
		
		return intervals;
	}
	
	protected boolean minLength1(String s, int numOps) {
		int cost0 = 0;
		int cost1 = 0;
		
		int len = s.length();
		for(int i = 0; i < len; i++) {
			char ch = s.charAt(i);
			int bit = ch - '0';
			
			if(bit != i % 2) {
				cost0++;
			}else {
				cost1++;
			}
		}
		
		return Math.min(cost0, cost1) <= numOps;
	}
	
	protected static record Interval(int begin, int end) {}
}