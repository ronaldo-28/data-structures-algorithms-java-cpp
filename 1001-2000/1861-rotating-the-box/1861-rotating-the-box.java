class Solution {
    public char[][] rotateTheBox(char[][] boxGrid) {
        
        // n x m rotated grid
        char[][] rotatedGrid = new char[boxGrid[0].length][boxGrid.length];

        for (int i = 0; i < boxGrid.length; i++) {
            processRow(boxGrid[i], rotatedGrid, boxGrid.length - 1 - i);
        }

        return rotatedGrid;
    }


    private void processRow(char[] row, char[][] rotatedGrid, int x) {

        int lowestBarrier = row.length;
        for (int curr = row.length - 1; curr >= 0; curr--) {
            
            if (row[curr] == '#') {
                lowestBarrier -= 1;
                rotatedGrid[lowestBarrier][x] = '#';

                if (curr != lowestBarrier) rotatedGrid[curr][x] = '.';
            } else if (row[curr] == '*') {
                lowestBarrier = curr;
                rotatedGrid[lowestBarrier][x] = '*';
            } else {
                rotatedGrid[curr][x] = '.';
            }

        }
    }
}

//     public char[][] rotateTheBox(char[][] boxGrid) {
        
//         // n x m rotated grid
//         char[][] rotatedGrid = new char[boxGrid[0].length][boxGrid.length];

//         for (int i = 0; i < boxGrid.length; i++) {
//             char[] newColumn = processRow(boxGrid[i]);

//             for (int j = 0; j < newColumn.length; j++) {

//                 rotatedGrid[j][boxGrid.length - 1 - i] = newColumn[j];
//             }
//         }

//         return rotatedGrid;
//     }


//     private char[] processRow(char[] row) {

//         char[] newColumn = new char[row.length];

//         int lowestBarrier = row.length;
//         for (int curr = row.length - 1; curr >= 0; curr--) {
            
//             if (row[curr] == '#') {
//                 lowestBarrier -= 1;
//                 newColumn[lowestBarrier] = '#';
//                 if (curr != lowestBarrier) newColumn[curr] = '.';
//             } else if (row[curr] == '*') {
//                 lowestBarrier = curr;
//                 newColumn[lowestBarrier] = '*';
//             } else {
//                 newColumn[curr] = '.';
//             }

//         }

//         return newColumn;
//     }