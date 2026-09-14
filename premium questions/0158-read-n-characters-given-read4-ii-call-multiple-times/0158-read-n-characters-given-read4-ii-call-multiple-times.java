/**
 * The read4 API is defined in the parent class Reader4.
 *     int read4(char[] buf4); 
 */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
     /*
     We need to support multiple calls to read()
    Meaning the leftover characters from a previous read4() must be reused in the next read().
    Therefore, we need persistent state:
    A buffer (char[4]) that holds chars returned by the most recent read4
    Two pointers:
    buf4Ptr → Index of next unread character in buf4
    buf4Cnt → How many characters were read by last read4()
    These must be class-level variables, not inside the method.
     */
    private char[] buf4 = new char[4];
    private int cursor = 0;
    private int count = 0;

    public int read(char[] buf, int n) {
        int total = 0;

        while (total < n) {

            // If local buffer empty, read new data
            if (cursor == count) {
                count = read4(buf4);
                cursor = 0;

                // EOF
                if (count == 0) break;
            }

            // Consume from buf4
            while (cursor < count && total < n) {
                buf[total++] = buf4[cursor++];
            }
        }

        return total;
    }
}