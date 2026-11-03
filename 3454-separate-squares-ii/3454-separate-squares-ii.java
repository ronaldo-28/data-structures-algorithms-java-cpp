/* Copyright (c) 2025 by https://leetcode.com/u/brinuke/. All rights reserved. */
class Solution {
	private static final Map.Entry<Integer, Integer> DUMMY_ENTRY
			= new java.util.AbstractMap.SimpleEntry<>(0, 0);

	private static double findHalfAreaLevel(Map<Integer, Integer> areaDelta) {
		int[] yLevels = new int[areaDelta.size()];
		int n = 0;
		for (Map.Entry<Integer, Integer> e : areaDelta.entrySet())
			if (e.getValue() != 0)
				yLevels[n++] = e.getKey();
		Arrays.sort(yLevels, 0, n);
		int y = yLevels[0];
		int meat = areaDelta.get(y); // total length of sweep line part covered by squares
		long area = 0;
		long[] areaBelow = new long[n];
		for (int i = 1; i < n; i++) {
			int prevY = y;
			y = yLevels[i];
			areaBelow[i] = area += (long) (y - prevY) * meat;
			meat += areaDelta.get(y);
		}
		int left = 0;
		int right = n - 1;
		area = (area + 1) / 2;
		while (true) {
			int mid = (left + right) >>> 1;
			if (mid == left)
				break;
			if (areaBelow[mid] < area)
				left = mid;
			else
				right = mid;
		}
		y = yLevels[left];
		area = areaBelow[left];
		return y + (yLevels[right] - y) * (areaBelow[n - 1] * 0.5 - area)
				/ (areaBelow[right] - area);
	}

	public static double separateSquares(int[][] squares) {
		TreeMap<Integer, Integer> plot = new TreeMap<>(); // xBegin to yLevel
		Map<Integer, Integer> areaDelta = new HashMap<>(); // yLevel to area derivative jump
		Arrays.sort(squares, (s1, s2) -> s1[1] - s2[1]); // by y
		plot.put(-1, -1);
		plot.put(Integer.MAX_VALUE, -1);
		int currentLevel = squares[0][1];
		int levelAreaDelta = 0;
		for (int[] square : squares) {
			int xb = square[0];
			int yb = square[1];
			int s = square[2];
			if (yb > currentLevel) {
				areaDelta.put(currentLevel, levelAreaDelta);
				levelAreaDelta = areaDelta.getOrDefault(currentLevel = yb, 0);
			}
			int xe = xb + s;
			int ye = yb + s;
			int x = xb;
			Integer yi = plot.get(x);
			int y = yi != null ? yi : 0;
			int prevTop = plot.lowerEntry(xb).getValue();
			if (y == 0 && prevTop < ye)
				plot.put(xb++, ye);
			Iterator<Map.Entry<Integer, Integer>> it = plot.tailMap(xb).entrySet().iterator();
			Map.Entry<Integer, Integer> e;
			if (y == 0) {
				y = prevTop; // take y value from left x
				prevTop = 0; // to prevent it.remove() below
				e = DUMMY_ENTRY; // for e.setValue(...) below
			} else
				e = it.next();
			while (true) {
				if (y <= ye)
					if (prevTop == ye)
						it.remove();
					else
						e.setValue(prevTop = ye);
				else
					prevTop = y;
				e = it.next();
				int x1 = e.getKey();
				if (y < ye) {
					int d = Math.min(x1, xe) - x;
					areaDelta.merge(ye, -d, Integer::sum);
					if (y > yb)
						areaDelta.merge(y, d, Integer::sum);
					else
						levelAreaDelta += d;
				}
				if (x1 >= xe) {
					if (x1 > xe) {
						if (y < prevTop)
							plot.put(xe, y);
					} else if (e.getValue() == prevTop)
						plot.remove(xe);
					break;
				}
				x = x1;
				y = e.getValue();
			}
		}
		areaDelta.put(currentLevel, levelAreaDelta);
		return findHalfAreaLevel(areaDelta);
	}
}