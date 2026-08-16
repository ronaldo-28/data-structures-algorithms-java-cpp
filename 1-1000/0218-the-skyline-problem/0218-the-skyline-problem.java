import java.util.*;

class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        if (buildings == null || buildings.length == 0) {
            return new ArrayList<>();
        }
        return new SkylineList(buildings);
    }

    private static class SkylineList extends AbstractList<List<Integer>> {
        private final int[][] buildings;
        private List<List<Integer>> result;

        private record Event(int position, int height, boolean isStart) {
            public static int compare(Event a, Event b) {
                if (a.position != b.position) {
                    return Integer.compare(a.position, b.position);
                }
                if (a.isStart && b.isStart) {
                    return Integer.compare(b.height, a.height);
                } else if (!a.isStart && !b.isStart) {
                    return Integer.compare(a.height, b.height);
                } else {
                    return a.isStart ? -1 : 1;
                }
            }
        }

        public SkylineList(int[][] buildings) {
            this.buildings = buildings;
        }

        private void initialize() {
            if (result != null) {
                return;
            }
            result = computeSkyline();
        }

        private List<List<Integer>> computeSkyline() {
            List<Event> events = new ArrayList<>(buildings.length * 2);
            for (int[] building : buildings) {
                int start = building[0];
                int end = building[1];
                int height = building[2];
                events.add(new Event(start, height, true));
                events.add(new Event(end, height, false));
            }
            events.sort(Event::compare);
            TreeMap<Integer, Integer> heightMap = new TreeMap<>();
            heightMap.put(0, 1);
            List<List<Integer>> skyline = new ArrayList<>();
            int prevMaxHeight = 0;
            for (Event event : events) {
                int position = event.position();
                int height = event.height();
                boolean isStart = event.isStart();
                if (isStart) {
                    heightMap.merge(height, 1, Integer::sum);
                } else {
                    int count = heightMap.get(height);
                    if (count == 1) {
                        heightMap.remove(height);
                    } else {
                        heightMap.put(height, count - 1);
                    }
                }
                int currentMaxHeight = heightMap.lastKey();
                if (currentMaxHeight != prevMaxHeight) {
                    skyline.add(List.of(position, currentMaxHeight));
                    prevMaxHeight = currentMaxHeight;
                }
            }
            return skyline;
        }

        @Override
        public List<Integer> get(int index) {
            initialize();
            return result.get(index);
        }

        @Override
        public int size() {
            initialize();
            return result.size();
        }
    }
}