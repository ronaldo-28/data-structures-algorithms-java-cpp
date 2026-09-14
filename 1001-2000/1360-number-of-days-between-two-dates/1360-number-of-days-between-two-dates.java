class Solution {
    public int daysBetweenDates(String date1, String date2) {
        int days1 = getDaysSince1970(date1);
        int days2 = getDaysSince1970(date2);
        return Math.abs(days2-days1);
    }
    
    // Days since (1970-12-31)
    public int getDaysSince1970 (String date) {
        // what about leap years? 2100 is NOT a leap year, 2000 IS a leap year.
        int YEAR_DAYS = 365;
        int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        int year = Integer.parseInt(date.substring(0,4));
        int month = Integer.parseInt(date.substring(5,7));
        int day = Integer.parseInt(date.substring(8,10));
        
        int res = day;
        for (int i = 0; i < month-1; i++) {
            res += months[i];
        }
        res += (year-1971) * YEAR_DAYS;
        
        if (year >= 1972) {
            res += (year - 1972) / 4 + 1;
        }
        
        if ((year % 4 == 0 && month <= 2) || year == 2100) {
            res -= 1;
        } 
        
        return res;
    }
    
}