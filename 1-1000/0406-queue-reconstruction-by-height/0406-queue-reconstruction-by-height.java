class Solution {
    public int[][] reconstructQueue(int[][] people) {
        quickS(people, 0, people.length - 1);
        List<int[]> list = new LinkedList();
        for(int i = 0; i < people.length; ++i) {
            list.add(people[i][1], people[i]);
        }
        return list.toArray(new int[people.length][]);
        // return people;
    }

    public void quickS(int[][] people, int left, int right) {
        if(left >= right) {
            return;
        }
        int mid = doQuick(people, left, right);
        quickS(people, left, mid - 1);
        quickS(people, mid + 1, right);
    }

    public int doQuick(int[][] people, int left, int right) {
        int[] temp = people[left];
        while(left < right) {
            while(left < right && compare(temp, people[right]) <= 0) {
                --right;
            }
            people[left] = people[right];
            while(left < right && compare(people[left], temp) <= 0) {
                ++left;
            }
            people[right] = people[left];
        }
        people[left] = temp;
        return left;
    }

    public int compare(int[] arr1, int[] arr2) {
        if(arr1[0] == arr2[0]) {
            return arr1[1] - arr2[1];
        }
        return arr2[0] - arr1[0];
    }
}