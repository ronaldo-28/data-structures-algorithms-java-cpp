class SnapshotArray {
    int snapCount = 0;
    List<Integer>[] snapArrayList;

    public SnapshotArray(int length) {
        snapArrayList = new ArrayList[length];
    }

    public void set(int index, int val) {
        List<Integer> indexList = snapArrayList[index];
        if (indexList == null) {
            indexList = new ArrayList<>();
        }
        
        int size = indexList.size();
        if (size - 1 == snapCount) {
            if (size != 0) {
                indexList.remove(size - 1);
            }
            indexList.add(val);
        } else {
            int lastValue = size > 0 ? indexList.get(size - 1) : 0;
            while (size < snapCount) {
                indexList.add(lastValue);
                size++;
            }
            indexList.add(val);
        }
        snapArrayList[index] = indexList;
    }

    public int snap() {
        snapCount++;
        return snapCount - 1;
    }

    public int get(int index, int snap_id) {
        List<Integer> indexList = snapArrayList[index];
        int size = (indexList != null) ? indexList.size() : 0;
        if (snap_id < size) {
            return indexList.get(snap_id);
        } else if (size == 0) {
            return 0;
        } else {
            return indexList.get(size - 1);
        }
    }
}


/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */