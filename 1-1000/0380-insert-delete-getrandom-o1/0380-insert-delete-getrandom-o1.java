 class RandomizedSet {
    private int[] values;
    private int[] indexMap;
    private boolean[] present;
    private int size;
    private final int CAPACITY = 100003; // large prime to avoid collisions
    private Random rand;

    public RandomizedSet() {
      values = new int[CAPACITY];
      indexMap = new int[CAPACITY];
      present = new boolean[CAPACITY];
      size = 0;
      rand = new Random();
    }

    public boolean insert(int val) {
      int hash = hash(val);
      if (present[hash])
        return false;
      values[size] = val;
      indexMap[hash] = size;
      present[hash] = true;
      size++;
      return true;
    }

    public boolean remove(int val) {
      int hash = hash(val);
      if (!present[hash])
        return false;
      int idx = indexMap[hash];
      int lastVal = values[size - 1];
      values[idx] = lastVal;
      indexMap[hash(lastVal)] = idx;
      present[hash] = false;
      size--;
      return true;
    }

    public int getRandom() {
      return values[rand.nextInt(size)];
    }

    private int hash(int val) {
      return (val % CAPACITY + CAPACITY) % CAPACITY;
    }
  }

  class RandomizedSet1 {
    private int[] values;
    private int[] indexMap;
    private boolean[] present;
    private int size;
    private final int CAPACITY = 100003; // large prime to avoid collisions
    private Random rand;

    public RandomizedSet1() {
      values = new int[CAPACITY];
      indexMap = new int[CAPACITY];
      present = new boolean[CAPACITY];
      size = 0;
      rand = new Random();
    }

    public boolean insert(int val) {
      int hash = hash(val);
      if (present[hash])
        return false;
      values[size] = val;
      indexMap[hash] = size;
      present[hash] = true;
      size++;
      return true;
    }

    public boolean remove(int val) {
      int hash = hash(val);
      if (!present[hash])
        return false;
      int idx = indexMap[hash];
      int lastVal = values[size - 1];
      values[idx] = lastVal;
      indexMap[hash(lastVal)] = idx;
      present[hash] = false;
      size--;
      return true;
    }

    public int getRandom() {
      return values[rand.nextInt(size)];
    }

    private int hash(int val) {
      return (val % CAPACITY + CAPACITY) % CAPACITY;
    }
  }

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */