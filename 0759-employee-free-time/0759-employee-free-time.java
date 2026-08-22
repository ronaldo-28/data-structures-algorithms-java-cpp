/*
// Definition for an Interval.
class Interval {
    public int start;
    public int end;

    public Interval() {}

    public Interval(int _start, int _end) {
        start = _start;
        end = _end;
    }
};
*/

class Solution {
public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {

    List<Interval> list = divide(schedule, 0, schedule.size() - 1);
    List<Interval> result = new ArrayList<>();
    for (int i = 1; i < list.size(); i ++) {
        result.add(new Interval(list.get(i - 1).end, list.get(i).start));
    } 
    
    return result;
    
}

private List<Interval> divide(List<List<Interval>> schedule, int start, int end) {
    if (start > end) {
        return new ArrayList<>();
    }
    if (start == end) {
        return schedule.get(start);
    }
    if (end - start == 1) {
        return conquer(schedule.get(start), schedule.get(end));
    }
    int mid = start + (end - start) / 2;
    List<Interval> left = divide(schedule, start, mid);
    List<Interval> right = divide(schedule, mid + 1, end);
    return conquer(left, right);
}

private List<Interval> conquer(List<Interval> left, List<Interval> right) {
    List<Interval> result = new ArrayList<>();
    int index1 = 0;
    int index2 = 0;
    Interval i1 = left.get(0);
    Interval i2 = right.get(0);
    while (i1 != null && i2 != null) {
        if (i1.end < i2.end) {
            if (i1.end < i2.start) {
                result.add(i1);
            } else {
                i2.start = Math.min(i1.start, i2.start);
                i2.end = Math.max(i1.end, i2.end);
            }
            index1 ++;
            if (index1 < left.size()) {
                i1 = left.get(index1);
            } else {
                i1 = null;
            }
        } else {
            if (i2.end < i1.start) {
                result.add(i2);
            } else {
                i1.start = Math.min(i1.start, i2.start);
                i1.end = Math.max(i1.end, i2.end);
            }
            index2 ++;
            if (index2 < right.size()) {
                i2 = right.get(index2);
            } else {
                i2 = null;
            }
        }
    }
    
    while (index1 < left.size()) {
        result.add(left.get(index1));
        index1 ++;
    }
    while (index2 < right.size()) {
        result.add(right.get(index2));
        index2 ++;
    }
    
    return result;
}
}