class Solution {
    public int convertTime(String cu, String co) {
        int h1 = cu.charAt(0);
        int h2 = cu.charAt(1);
        int hcu = h1*10+h2;

        h1 = co.charAt(0);
        h2 = co.charAt(1);
        int hco = h1*10+h2;

        int m1 = cu.charAt(3);
        int m2 = cu.charAt(4);
        int mcu = m1*10+m2;

        m1 = co.charAt(3);
        m2 = co.charAt(4);
        int mco = m1*10+m2;

        int change = (hco*60+mco) - (hcu*60+mcu);
        int ans = 0;
        if(change>=60){
            ans += (change)/60;
            change %= 60;
        } 
        if(change>=15){
            ans += (change)/15;
            change %= 15;
        } 
        if(change>=5){
            ans += (change)/5;
            change %= 5;
        } 
        ans += change;
        return ans;
    }
}