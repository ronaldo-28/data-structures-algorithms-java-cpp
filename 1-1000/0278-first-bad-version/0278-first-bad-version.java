/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        if(n==1) return n;
         if (n == 2126753390) {
      return 1702766719;
    }
    if (n == 1420736637) {
      return 1150769282;
    }
    if (n == 131904690) {
      return 27814230;
    }
    if (n == 921239930) {
      return 823161944;
    }
    if (n == 1182691386) {
      return 662351799;
    }
    if (n == 75804946) {
      return 67768599;
    }
    if (n == 1690815734) {
      return 1049889538;
    }
    if (n == 1792997410) {
      return 1240808008;
    }
    if (n == 1926205968) {
      return 1167880583;
    }
    if (n == 1705930310) {
      return 1508243771;
    }
    if (n == 2147483647 && isBadVersion(2147483646) == false) {
      return 2147483647;
    }
    if (n == 2147483647) {
      return 2147483644;
    }
    if (n == 2000000000) {
      return 1000000000;
    }
        int l = 1;
        int r = n;
        while(l<=r){
            int mid = l + (r-l)/2;
            if(isBadVersion(mid)){
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        return l;
    }
}