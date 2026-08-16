class Solution {
    private void sort(String[] products) {
        Collections.shuffle(Arrays.asList(products));
        sort(products, 0, products.length, 0);
    }

    private void sort(String[] products, int start, int end, int d) {
        if (end - start <= 1) {
            return;
        }
        int pivot = charAt(products[start], d);
        int left = start;
        int mid = start;
        int right = end - 1;
        while (mid <= right) {
            if (charAt(products[mid], d) < pivot) {
                swap(products, left++, mid++);
            } else if (charAt(products[mid], d) > pivot) {
                swap(products, mid, right--);
            } else {
                mid++;
            }
        }
        sort(products, start, left, d);
        if (pivot >= 0) {
            sort(products, left, mid, d + 1);
        }
        sort(products, right + 1, end, d);
    }

    private void swap(String[] products, int index1, int index2) {
        String temp = products[index1];
        products[index1] = products[index2];
        products[index2] = temp;
    }

    private int find(String[] products, int start, int end, int index, char ch) {
        int left = start;
        int right = end - 1;
        if (right < left) {
            return start;
        }
        if (charAt(products[start], index) >= ch) {
            return start;
        }
        if (charAt(products[right], index) < ch) {
            return end;
        }
        while ((right - left) > 1) {
            int mid = (left + right) / 2;
            if (charAt(products[mid], index) >= ch) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    private int charAt(String string, int index) {
        return index < string.length() ? string.charAt(index) : -1;
    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        sort(products);
        int start = 0;
        int end = products.length;
        List<List<String>> results = new ArrayList<>(searchWord.length());
        for (int i = 0; i < searchWord.length(); i++) {
            List<String> matches = new ArrayList<>(3);
            char c = searchWord.charAt(i);
            start = find(products, start, end, i, c);
            end = find(products, start, end, i, (char) (c+1));
            for (int j = start; j < Math.min(end, start + 3); j++) {
                matches.add(products[j]);
            }
            results.add(matches);
        }
        return results;
    }

}