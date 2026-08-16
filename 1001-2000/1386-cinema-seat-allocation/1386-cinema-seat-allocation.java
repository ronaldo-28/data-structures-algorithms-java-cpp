class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        // Map to capture reserved seats provided by "reservedSeats" array in bits
        Map<Integer, Integer> rowSeatMap = new HashMap<>();

        for (int[] reservation : reservedSeats) {
            // Each reservation has row as 1st element, and seatNumber as 2nd element
            int row = reservation[0];
            int seatNumber = reservation[1];

            // We are only checking the seats from 2 to 9, and ignoring 1 and 10 seats
            if (seatNumber > 1 && seatNumber < 10) {
                // 10th seat is 0 bit, 9th is 1 bit and so on
                rowSeatMap.merge(row, 1 << (10 - seatNumber), (existing, newBit) -> existing | newBit);
            }
        }

        // 1 2 3 4 5 6 7 8 9 10
        // 0 1 1 1 1 0 0 0 0 0  - leftMask [2,3,4,5]
        // 0 0 0 1 1 1 1 0 0 0  - middleMask [4,5,6,7]
        // 0 0 0 0 0 1 1 1 1 0  - rightMask [6,7,8,9]
        int leftGroupMask = 0b0111100000;
        int middleGroupMask = 0b0001111000;
        int rightGroupMask = 0b0000011110;

        int[] maskGroups = { leftGroupMask, middleGroupMask, rightGroupMask };

        int totalFamilies = (n - rowSeatMap.size()) * 2; // if a row is completely empty, book 2 families

        for (int reservedSeatBitMask : rowSeatMap.values()) {
            for (int maskGroup : maskGroups) {

                // If the seats are available for booking, then the AND operation will be 0
                if ((reservedSeatBitMask & maskGroup) == 0) {
                    reservedSeatBitMask |= maskGroup;
                    totalFamilies++;
                }
            }
        }
        return totalFamilies;
    }
}