class Solution {

    private record Element(int val, int row) {}

    public int[] smallestRange(List<List<Integer>> nums) {
        int n = nums.size();

        var merged = new ArrayList<Element>();
        for (int i = 0; i < n; i++) {
            for (int num : nums.get(i)) {
                merged.add(new Element(num, i));
            }
        }

        Element[] elements = merged.toArray(Element[]::new);
        Arrays.sort(elements, (a, b) -> a.val - b.val);

        int resStart = elements[0].val;
        int resEnd = elements[elements.length - 1].val;

        var map = new int[n];
        int count = 0;
        int start = 0;

        for (Element endElement : elements) {
            if (++map[endElement.row] == 1) {
                count++;
            }

            while (count == n) {
                Element startElement = elements[start++];

                if (endElement.val - startElement.val < resEnd - resStart) {
                    resStart = startElement.val;
                    resEnd = endElement.val;
                }

                if (--map[startElement.row] == 0) {
                    count--;
                }
            }
        }

        return new int[] {resStart, resEnd};
    }
}