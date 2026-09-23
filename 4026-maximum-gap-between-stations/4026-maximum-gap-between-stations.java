
class Solution {
    public int maximumGap(String skill, String station) {
        if(skill.length()==1) return 0;

        return maximumGap(skill.toCharArray(), station.toCharArray());
    }


    private static int maximumGap(char[] skill, char[] station) {
        int n = skill.length;
        var last = new int[n];

        int pos = station.length - 1;
        for(int i=n-1; i>=0; --i) {
            while(station[pos]!=skill[i]) {
                --pos;
            }
            last[i] = (pos--);
        }

        int result = 0;
        pos = 0;
        for(int i=0, limit=n-1; i<limit; ++i) {
            while(station[pos]!=skill[i]) {
                ++pos;
            }
            result = Math.max(result, last[i+1] - pos);
            ++pos;
        }

        return result;
    }
}