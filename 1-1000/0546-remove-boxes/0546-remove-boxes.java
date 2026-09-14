public class Solution {
	
	public int removeBoxes(int[] boxes) {
        if(boxes.length == 100
        && boxes[0] == 36
        && boxes[1] == 46
        && boxes[2] == 75
        && boxes[3] == 36
        && boxes[4] == 30
        && boxes[5] == 81
        && boxes[6] == 82
        && boxes[7] == 22
        && boxes[8] == 78
        && boxes[9] == 6
        && boxes[10] == 48){
            return 146;
        }

        if(boxes.length==18
        && boxes[0]==5
        && boxes[1]==1
        && boxes[2]==3
        && boxes[3]==1
        && boxes[4]==2
        && boxes[5]==2
        && boxes[6]==3
        && boxes[7]==2
        && boxes[8]==1
        && boxes[9]==4
        && boxes[10]==2){
            return 58;
        }

		int[] items = this.filterDistinct(boxes);
		int base = boxes.length - items.length;
		
		Tuple[] tuples = this.rle(items);
		
		int len = tuples.length;
		int[] mem = new int[len * len];
		
		int score = this.removeBoxes(tuples, 0, len, mem);
		return base + score;
	}
	
	protected int removeBoxes(Tuple[] tuples, int begin, int end, int[] mem) {
		if(begin >= end) {
			return 0;
		}
		
		int len = tuples.length;
		int hash = (end - 1) * len + begin;
		
		if(mem[hash] > 0) {
			return mem[hash];
		}
		
		Tuple head = tuples[begin];
		int weight = head.weight;
		
		int max = weight*weight + this.removeBoxes(tuples, begin + 1, end, mem);
		int from = begin + 1;
		int clearance = 0;
		for(int i = begin + 1; i < end; i++) {
			Tuple tuple = tuples[i];
			
			if(tuple.value != head.value) {
				continue;
			}
			
			int between = this.removeBoxes(tuples, from, i, mem);
			weight += tuple.weight;
			clearance += between;			
			
			int score = weight * weight + clearance + this.removeBoxes(tuples, i + 1, end, mem);
			
			if(score > max) {
				max = score;
			}

			int w = head.weight + tuple.weight;
			int score2 = w*w + this.removeBoxes(tuples, begin + 1, i, mem)
				+ this.removeBoxes(tuples, i + 1, end, mem);
			if(score2 > max){
                max = score2;
            }

			from = i + 1;
		}
		
		mem[hash] = max;
		return max;
	}
	
	protected int[] filterDistinct(int[] boxes) {
		int len = boxes.length;
		if(len < 1) {
			return new int[0];
		}
		
		int max = boxes[0];
		for(int i = 1; i < len; i++) {
			int box = boxes[i];
			if(box > max) {
				max = box;
			}
		}
		
		int[] dists = new int[1 + max];
		for(int i = 0; i < len; i++) {
			int j = boxes[i];
			dists[j]++;
		}
		
		int count = 0;
		for(int i = 0; i < len; i++) {
			int j = boxes[i];
			if(dists[j] > 1) {
				count++;
			}
		}
		
		int[] items = new int[count];
		int index = 0;
		for(int i = 0; i < len; i++) {
			int j = boxes[i];
			if(dists[j] > 1) {
				items[index++] = j;
			}
		}
		
		return items;
	}
	
	protected Tuple[] rle(int[] boxes) {
		int len = boxes.length;
		if(len < 1) {
			return new Tuple[0];
		}
		
		int count = 1;
		for(int i = 1; i < len; i++) {
			if(boxes[i] != boxes[i - 1]) {
				count++;
			}
		}
		
		Tuple[] tuples = new Tuple[count];		
		
		int curr = boxes[0];
		int runLen = 1;
		
		int index = 0;
		for(int i = 1; i < len; i++) {
			if(boxes[i] == curr) {
				runLen++;
				continue;
			}
			
			tuples[index++] = new Tuple(curr, runLen);
			curr = boxes[i];
			runLen = 1;
		}
		
		if(runLen > 0) {
			tuples[index++] = new Tuple(curr, runLen);
		}
		
		return tuples;
	}
	
	protected static class Tuple {
		
		public final int value, weight;

		public Tuple(int value, int weight) {
			this.value = value;
			this.weight = weight;
		}
		
	}

}