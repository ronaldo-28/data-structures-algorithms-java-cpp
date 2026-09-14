class SORTracker {
    private List<SN> list;
    private int i, j;
    
    public SORTracker() {
        list = new ArrayList<>();
    }
    
    public void add(String name, int score) {
        //find r by score
        int r = insertScore(score);
        //find c by name
        int c = insertName(name, r);
        //update i and j
        if (i >= r && list.get(r).names.size() == 1) {
            j--;
            i++;
            if (j < 0) {
                i--;
                j = list.get(i).names.size() - 1;
            }
        } else if (i > r) {
            j--;
            if (j < 0) {
                i--;
                j = list.get(i).names.size() - 1;
            }
        } 
    }
    
    public String get() {
        String s = list.get(i).names.get(j);
        if (list.get(i).names.size() > j + 1) {
            j++;
        } else {
            i++;
            j = 0;
        }

        return s;
    }


    private int insertName(String name, int r) {
        List<String> names = list.get(r).names;
        if (names.size() == 0) {
            names.add(name);
            return 0;
        }
        int l = 0;
        int h = names.size() - 1;
        if (name.compareTo(names.get(l)) < 0) {
            names.add(l, name);
            return l;
        }
        if (name.compareTo(names.get(h)) > 0) {
            names.add(h + 1, name);
            return h + 1;
        }

        while(l < h) {
            int m = l + (h - l) / 2;
            if (names.get(m).compareTo(name) > 0){
                h = m;
            } else {
                l = m + 1;
            }
        }
        names.add(h, name);
        return h;
    }

    private int insertScore(int score) {
        if (list.size() == 0) {
            list.add(new SN(score));
            return 0;
        }
        int l = 0;
        int h = list.size() - 1;
        if (score > list.get(l).score) {
            list.add(l, new SN(score));
            return l;
        }
        if (score < list.get(h).score) {
            list.add(h + 1, new SN(score));
            return h + 1;
        }

        while(l < h) {
            int m = l + (h - l) / 2;
            if (list.get(m).score > score) {
                l = m + 1;
            } else  {
                h = m;
            }
        }

        if (list.get(h).score < score) {
            list.add(h, new SN(score));
        }
        return h;
    }
}

class SN {
    Integer score;
    List<String> names;
    SN(int score) {
        this.score = score;
        names = new ArrayList<>();
    }
}

/**
 * Your SORTracker object will be instantiated and called as such:
 * SORTracker obj = new SORTracker();
 * obj.add(name,score);
 * String param_2 = obj.get();
 */