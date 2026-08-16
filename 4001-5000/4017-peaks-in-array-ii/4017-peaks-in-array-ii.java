	class Solution {
		long tri(int a, int b)
		{
			long d = b - a;
			return d * (d + 1) / 2;
		}

		void flip(int x, LST peaks, long[] ft)
		{
			if(peaks.get(x)){
				int ne = peaks.next(x+1);
				int pr = peaks.prev(x-1);
				if(ne != -1){
					addFenwick(ft, x, -tri(x, ne));
				}
				if(pr != -1){
					addFenwick(ft, pr, -tri(pr, x));

					if(ne != -1){
						addFenwick(ft, pr, tri(pr, ne));
					}
				}

				peaks.unset(x);
			}else{
				int ne = peaks.next(x+1);
				int pr = peaks.prev(x-1);
				if(pr != -1 && ne != -1){
					addFenwick(ft, pr, -tri(pr, ne));
				}
				if(pr != -1){
					addFenwick(ft, pr, tri(pr, x));
				}
				if(ne != -1){
					addFenwick(ft, x, tri(x, ne));
				}
				peaks.set(x);
			}
		}

		public long[] countOfPeaks(int[] nums, int[][] queries) {
			int n = nums.length;

			LST peaks = new LST(n);

			for(int i = 1; i < n-1; i++){
				if(nums[i-1] < nums[i] && nums[i] > nums[i+1]){
					peaks.set(i);
				}
			}

			long[] ft = new long[n+5];

			for(int i = peaks.next(0); i != -1; i = peaks.next(i+1)){
				int ne = peaks.next(i+1);
				if(ne != -1){
					addFenwick(ft, i, tri(i, ne));
				}
			}

			long[] ans = new long[queries.length];
			int p = 0;

			for(int[] q : queries){
				if(q[0] == 1){
					int l = q[1];
					int r = q[2];
					int fp = peaks.next(l+1);

					if(fp == -1 || fp >= r){
						ans[p++] = 0;
						continue;
					}

					int lp = peaks.prev(r-1);
					long total = tri(l, r);

					long minus = tri(l, fp);
					minus += tri(lp, r);
					if(fp != lp){
						minus += sumFenwick(ft, lp-1)
								- sumFenwick(ft, fp-1);
					}

					ans[p++] = total - minus;

				}else{
					int id = q[1];
					int v = q[2];

					int ll = Math.max(id-1, 1);
					int rr = Math.min(id+1, n-2);

					// 古い peak 状態を削除
					for(int i = ll; i <= rr; i++){
						if(nums[i-1] < nums[i] && nums[i] > nums[i+1]){
							flip(i, peaks, ft);
						}
					}

					nums[id] = v;

					for(int i = ll; i <= rr; i++){
						if(nums[i-1] < nums[i] && nums[i] > nums[i+1]){
							flip(i, peaks, ft);
						}
					}
				}
			}

			return Arrays.copyOf(ans, p);
		}

		public static long sumFenwick(long[] ft, int i)
		{
			long sum = 0;
			for(i++;i > 0;i -= i&-i)sum += ft[i];
			return sum;
		}

		public static void addFenwick(long[] ft, int i, long v)
		{
			if(v == 0)return;
			int n = ft.length;
			for(i++;i < n;i += i&-i)ft[i] += v;
		}


		public static class LST {
			public long[][] set;
			public int n;
			//	public int size;

			public LST(int n) {
				this.n = n;
				int d = 1;
				for(int m = n;m > 1;m>>>=6, d++);

				set = new long[d][];
				for(int i = 0, m = n>>>6;i < d;i++, m>>>=6){
					set[i] = new long[m+1];
				}
				//		size = 0;
			}

			// [0,r)
			public LST setRange(int r)
			{
				for(int i = 0;i < set.length;i++, r=r+63>>>6){
					for(int j = 0;j < r>>>6;j++){
						set[i][j] = -1L;
					}
					if((r&63) != 0)set[i][r>>>6] |= (1L<<r)-1;
				}
				return this;
			}

			// [0,r)
			public LST unsetRange(int r)
			{
				if(r >= 0){
					for(int i = 0;i < set.length;i++, r=r+63>>>6){
						for(int j = 0;j < r+63>>>6;j++){
							set[i][j] = 0;
						}
						if((r&63) != 0)set[i][r>>>6] &= -(1L << r);
					}
				}
				return this;
			}

			public LST set(int pos)
			{
				if(pos >= 0 && pos < n){
					//			if(!get(pos))size++;
					for(int i = 0;i < set.length;i++, pos>>>=6){
						set[i][pos>>>6] |= 1L<<pos;
					}
				}
				return this;
			}

			public LST unset(int pos)
			{
				if(pos >= 0 && pos < n){
					//			if(get(pos))size--;
					for(int i = 0;i < set.length && (i == 0 || set[i-1][pos] == 0L);i++, pos>>>=6){
						set[i][pos>>>6] &= ~(1L<<pos);
					}
				}
				return this;
			}

			public boolean get(int pos)
			{
				return pos >= 0 && pos < n && set[0][pos>>>6]<<~pos<0;
			}

			public LST toggle(int pos)
			{
				return get(pos) ? unset(pos) : set(pos);
			}

			public int prev(int pos)
			{
				for(int i = 0;i < set.length && pos >= 0;i++, pos>>>=6, pos--){
					int pre = prev(set[i][pos>>>6], pos&63);
					if(pre != -1){
						pos = pos>>>6<<6|pre;
						while(i > 0)pos = pos<<6|63-Long.numberOfLeadingZeros(set[--i][pos]);
						return pos;
					}
				}
				return -1;
			}

			public int next(int pos)
			{
				for(int i = 0;i < set.length && pos>>>6 < set[i].length;i++, pos>>>=6, pos++){
					int nex = next(set[i][pos>>>6], pos&63);
					if(nex != -1){
						pos = pos>>>6<<6|nex;
						while(i > 0)pos = pos<<6|Long.numberOfTrailingZeros(set[--i][pos]);
						return pos;
					}
				}
				return -1;
			}

			private static int prev(long set, int n)
			{
				long h = set<<~n;
				if(h == 0L)return -1;
				return -Long.numberOfLeadingZeros(h)+n;
			}

			private static int next(long set, int n)
			{
				long h = set>>>n;
				if(h == 0L)return -1;
				return Long.numberOfTrailingZeros(h)+n;
			}

			@Override
			public String toString()
			{
				List<Integer> list = new ArrayList<>();
				for(int pos = next(0);pos != -1;pos = next(pos+1)){
					list.add(pos);
				}
				return list.toString();
			}
		}

	}