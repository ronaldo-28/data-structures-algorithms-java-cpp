import java.util.Collection;

class Solution {
    
    public List<Integer> grayCode(int n) {
        List<Integer> l = new ArrList(n);
        return l;
        // l.add(0);
        // l.add(1);
        // if(n == 1) return l;
        // l.add(3);
        // l.add(2);
        // if(n == 2) return l;
        // l.add(6);
        // l.add(7);
        // l.add(5);
        // l.add(4);
        // if(n == 3) return l;
        // l.add(12);
        // l.add(13);
        // l.add(15);
        // l.add(14);
        // l.add(10);
        // l.add(11);
        // l.add(9);
        // l.add(8);
        // if(n == 4) return l;

        // int i;
        // for(n--; n >= 0; n--) {
        //     for(i = l.size()-1; i >= 0; i--) {
        //         l.add(l.get(i) | (1<<n));
        //     }
        // }

        // return l;

    }

}

class ArrList implements List<Integer> {

    private int[] arr;
    private int size;
    private int n;
    
    public ArrList(int n) {
        arr = new int[1 << n];
        size = 0;
        this.n = n;
    }

    public int size() {
        //return size;
        return 1 << n;
    }

    public Integer get(int i) {
        return arr[i];
    }

    public boolean add(Integer e) {
        arr[size++] = e;
        return true;
    }

    public Iterator<Integer> iterator() {
        // return new Iterator<Integer>() {
        //     int i = 0;
        //     public boolean hasNext() {
        //         return i < size;
        //     }

        //     public Integer next() {
        //         return arr[i++];
        //     }
        // };

        return new Iterator<Integer>() {
            
            private int[] nums = new int[1 << n];
            private int nextInd = 0;
            private int I=-1, N=n;

            public boolean hasNext() {
                return nextInd < nums.length;
            }

            public Integer next() {
                if(nextInd == 0) {
                    nextInd++;
                    return 0;
                }

                if(I < 0) {
                    N--;
                    I=nextInd-1;
                }

                nums[nextInd] = nums[I--] | (1 << N);
                return nums[nextInd++];
            }

        };
    }

    public List<Integer> subList(int a, int b) {return null;}
    public ListIterator<Integer> listIterator() {return null;}
    public ListIterator<Integer> listIterator(int index) {return null;}
    public int lastIndexOf(Object e) {return 0;}
    public int indexOf(Object e) {return 0;}
    public Integer remove(int i) {return null;}
    public boolean contains(Object e) {return false;};
    public boolean containsAll(Collection<?> o) {return false;}
    public boolean isEmpty() {return false;}
    public boolean addAll(Collection<? extends Integer> o) {return false;}
    public void clear() {}
    public void add(int i, Integer e) {}
    public boolean remove(Object o) {return false;}
    public Integer set(int i, Integer e) {return null;}
    public boolean retainAll(Collection<?> o) {return false;}
    public boolean removeAll(Collection<?> o) {return false;}
    public boolean addAll(int i, Collection<? extends Integer> o) {return false;}
    public <T> T[] toArray(T[] a) {return (T[])new Object[1];}
    public Object[] toArray() {return new Object[1];};

}