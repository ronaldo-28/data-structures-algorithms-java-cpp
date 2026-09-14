class Solution {
    List<String> res;
    String s;
    public List<String> restoreIpAddresses(String s) {
        res = new ArrayList<>();
        if(s.length() > 12) return res; 
        this.s = s;
        build(0,new StringBuilder(),-1,0);
        return res;
    }

    private void build(int index, StringBuilder sb, int val, int dots){
        if(index == s.length()){
            // System.out.println(sb.toString());
            if(dots == 3 && val >= 0 && val <= 255) {
                res.add(sb.toString());
            }
            return;
        }
        
        if(val == 0) return;
        int sbLen = sb.length();

        if(val == -1) val = 0;

        sb.append(s.charAt(index));
        val *= 10;
        val +=  (int) s.charAt(index) - '0';
        build(index+1,sb,val,dots);
        sb.setLength(sbLen);
        //cant have a leading 0
        //if this is valid place optionally place a dot

        if(val >= 0 && val <=255 && dots < 3){
            sb.append(s.charAt(index));
            sb.append('.');
            build(index+1,sb,-1,dots+1);
            sb.setLength(sbLen);
        } 
        return;
    }

}