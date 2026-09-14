// /*

// DFS 算出4个角落的坐标，根据公式求面积
// m*n，空间m*n
//  */
// // class Solution {

// //     int top, bottom, left, right;
// //     public int minArea(char[][] image, int x, int y) {

// //         if(image.length == 0)return 0;
// //         top= bottom = x;
// //         left = right = y;

// //         dfs(image, x, y);
// //         return (bottom-top)*(right-left);
// //     }

// //     public void dfs(char[][] image, int x, int y){
// //         if(x <0 || y<0 || x>= image.length || y>= image[0].length || image[x][y] == '0') return;
// //         image[x][y] = '0';
// //         top = Math.min(x, top);
// //         bottom = Math.max(bottom, x+1);
// //         left = Math.min(y, left);
// //         right = Math.max(right, y+1);

// //         dfs(image, x+1, y);
// //         dfs(image, x-1, y);
// //         dfs(image, x, y-1);
// //         dfs(image, x, y+1);
// //         return;
// //     }

// // }

// /*
// 实际应用：马赛克，用最小区域的马赛克。要不是圆形/矩形
// 首先确定4个角落的1，来确定矩形的位置。
// 根据x，y找到一个connected component就行。
 
// Brute force：
// (x,y) 看y-1，如果y-1列有1就继续y--，如果没有1了，这个y-1就是边界了。
// 同理看y+1，x-1，x+1.
// 时间复杂度mxn.

// 那就binary search，就像 first bad version。可以折半verify。
 
// 左边界：[0,y] all col are 0, go right;
//  		    If any 1exit, go left           start is left boundary


// 时间复杂度(m*logn + n*logm)


// */
// class Solution {
//     public int minArea(char[][] image, int x, int y) {
//         //cc

//         int left = 0;
//         int right = 0;
//         int top = 0;
//         int down = 0;

//         int row = image.length;
//         int col = image[0].length;

//         // left: [0,y]
//         //检查左边缘，看没有col 有1
//         int start = 0;
//         int end = y;
//         while (start <= end) {
//             int mid = start + (end - start) / 2;
//             if (exitOneInCol(image, mid)) {
//                 end = mid - 1;
//             } else {
//                 start = mid + 1;
//             }
//         }
//         left = start;
// //            System.out.println(left);

//         // right: [y,col-1]
//         start = y;
//         end = col - 1;
//         while (start <= end) {
//             int mid = start + (end - start) / 2;
//             if (exitOneInCol(image, mid)) {
//                 start = mid + 1;
//             } else {
//                 end = mid - 1;
//             }
//         }
//         right = end;
// //            System.out.println(right);

//         // top: [0,x]
//         start = 0;
//         end = x;
//         while (start <= end) {
//             int mid = start + (end - start) / 2;
//             if (exitOneInRow(image, mid)) {
//                 end = mid - 1;
//             } else {
//                 start = mid + 1;
//             }
//         }
//         top = start;
// //            System.out.println(top);

//         // down: [x, row-1]
//         start = x;
//         end = row - 1;
//         while (start <= end) {
//             int mid = start + (end - start) / 2;
//             if (exitOneInRow(image, mid)) {
//                 start = mid + 1;
//             } else {
//                 end = mid - 1;
//             }
//         }
//         down = end;
// //            System.out.println(down);
//         return (right - left + 1) * (down - top + 1);

//     }

//     private boolean exitOneInCol(char[][] image, int mid) {
//         int row = image.length, col = image[0].length;
//         for (int i = 0; i < row; i++) {
//             if (image[i][mid] == '1') {
//                 return true;
//             }
//         }
//         return false;

//     }

//     private boolean exitOneInRow(char[][] image, int mid) {
//         int row = image.length, col = image[0].length;
//         for (int j = 0; j < col; j++) {
//             if (image[mid][j] == '1') {
//                 return true;
//             }
//         }
//         return false;
//     }
// }
class Solution {
    public int minArea(char[][] image, int x, int y) {

        int m = image.length;
        int n = image[0].length;

        int top = searchTop(image, 0, x);
        int bottom = searchBottom(image, x, m - 1);

        int left = searchLeft(image, 0, y);
        int right = searchRight(image, y, n - 1);

        return (bottom - top + 1) * (right - left + 1);
    }


    // find first row that has black pixel
    private int searchTop(char[][] image, int left, int right) {

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (hasBlackInRow(image, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }


    // find last row that has black pixel
    private int searchBottom(char[][] image, int left, int right) {

        while (left < right) {

            int mid = left + (right - left + 1) / 2;

            if (hasBlackInRow(image, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }


    // find first column that has black pixel
    private int searchLeft(char[][] image, int left, int right) {

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (hasBlackInCol(image, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }


    // find last column that has black pixel
    private int searchRight(char[][] image, int left, int right) {

        while (left < right) {

            int mid = left + (right - left + 1) / 2;

            if (hasBlackInCol(image, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }


    private boolean hasBlackInRow(char[][] image, int row) {

        for (int col = 0; col < image[0].length; col++) {
            if (image[row][col] == '1') {
                return true;
            }
        }

        return false;
    }


    private boolean hasBlackInCol(char[][] image, int col) {

        for (int row = 0; row < image.length; row++) {
            if (image[row][col] == '1') {
                return true;
            }
        }

        return false;
    }
}