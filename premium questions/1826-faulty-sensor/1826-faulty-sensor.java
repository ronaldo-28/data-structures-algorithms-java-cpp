class Solution {
    public int badSensor(int[] o, int[] t) {
        boolean one = canShift(o, t); //can o be shfited to t?
        boolean two = canShift(t, o); //can t be shifted to o?
        if (one == two) return -1;    //both true or both false, then we can't tell
        return one? 1 : 2;
    }

    private static boolean canShift(int[] o, int[] t){ //return true if o can be shifted to t
        int i = 0, j = 0;
        while(j < o.length){
            if (o[i] != t[j]) j++;
            else {i++; j++;}
        }

        return i == o.length - 1; 
		//there should be only 1 mismatch, so i has to be at o.length - 1
    }
}