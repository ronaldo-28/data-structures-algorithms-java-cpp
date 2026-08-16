 class Solution {

    record Element(int speed, int efficiency) {
    }

    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {

      var arr = new Element[n];
      for (int i = 0; i < n; i++) {
        arr[i] = new Element(speed[i], efficiency[i]);
      }

      // Sort by efficiency descending
      sort(arr);

      Queue<Integer> minHeap_speed = new PriorityQueue<>();
      long speedSum = 0;
      long maxScore = 0;

      for (var curr : arr) {
        minHeap_speed.add(curr.speed);
        speedSum += curr.speed;

        if (minHeap_speed.size() > k) {
          speedSum -= minHeap_speed.poll(); // remove smallest speed
        }

        long performance = speedSum * curr.efficiency; // This will be smallest, coz sorted
        maxScore = Math.max(maxScore, performance);
      }

      return (int) (maxScore % 1_000_000_007);
    }

    void sort(Element[] arr) {
      partion(arr, 0, arr.length - 1);
    }

    private void partion(Element[] arr, int left, int right) {
      if (left >= right)
        return;

      int pivot = arr[right].efficiency;
      int i = left - 1;
      for (int j = left; j < right; j++) {
        if (arr[j].efficiency > pivot) {
          i++;
          swap(arr, i, j);
        }
      }
      swap(arr, i + 1, right);
      partion(arr, left, i);
      partion(arr, i + 2, right);
    }

    private void swap(Element[] arr, int i, int j) {
      Element temp = arr[i];
      arr[i] = arr[j];
      arr[j] = temp;
    }
  }