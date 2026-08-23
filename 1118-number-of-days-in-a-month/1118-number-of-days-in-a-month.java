class Solution {
  int[] DAYS_BY_MONTH = {-1, 31, -1, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

  public int numberOfDays(int year, int month) {
    if (month == 2) return isLeap(year) ? 29 : 28;
    return DAYS_BY_MONTH[month];
  }

  private boolean isLeap(int y) {
    if (y % 4 != 0) return false;
    if (y % 100 == 0 && y % 400 != 0) return false;
    return true;
  }
}