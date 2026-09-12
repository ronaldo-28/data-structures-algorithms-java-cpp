class Solution {
  public String shortestSuperstring(String s1, String s2) {
    if (s1.contains(s2)) return s1;
    if (s2.contains(s1)) return s2;

    for (int overlapCount = Math.min(s1.length(), s2.length()); overlapCount > 0; overlapCount--)
      if (isOverlaptingBackToStart(s2, s1, overlapCount)) 
        return s2 + s1.substring(overlapCount);
      else if (isOverlaptingBackToStart(s1, s2, overlapCount)) 
        return s1 + s2.substring(overlapCount);

    return s1 + s2;
  }

  private boolean isOverlaptingBackToStart(String backOne, String startOne, int overlapCount) {
    for (int i = 0; i < overlapCount; i++)
      if (startOne.charAt(i) != backOne.charAt(backOne.length() - overlapCount + i))
        return false;
    
    return true;
  }
}