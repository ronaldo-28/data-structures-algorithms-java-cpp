/**
 * Definition for a street.
 * class Street {
 *     public Street(int[] doors);
 *     public void openDoor();
 *     public void closeDoor();
 *     public boolean isDoorOpen();
 *     public void moveRight();
 *     public void moveLeft();
 * }
 */
class Solution {
    public int houseCount(Street street, int k) {
        for (int i = 0; i < k; ++i) {
            street.closeDoor();
            street.moveLeft();
        }
        street.openDoor();
        int r = 1;
        for (street.moveLeft(); !street.isDoorOpen(); street.moveLeft(), ++r)
        ;
        return r;
    }
}