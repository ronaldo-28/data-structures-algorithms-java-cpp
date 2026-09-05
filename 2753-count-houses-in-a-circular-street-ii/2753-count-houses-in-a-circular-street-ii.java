/**
 * Definition for a street.
 * class Street {
 *     public Street(int[] doors);
 *     public void closeDoor();
 *     public boolean isDoorOpen();
 *     public void moveRight();
 * }
 */
class Solution {
    public int houseCount(Street street, int k) {
        while(!street.isDoorOpen())
            street.moveRight();
        street.moveRight();
        int i=0;
        int last=0;
        while(i<k)
            {
                i++;
                if(street.isDoorOpen())
                {
                    last=i;
                    street.closeDoor();
                }
                street.moveRight();
            }
            return last;      
    }
}