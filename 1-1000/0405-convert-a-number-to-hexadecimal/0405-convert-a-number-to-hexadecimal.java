public class Solution {
    public String toHex(int num) {
        // If the number is zero, return "0"
        if (num == 0) {
            return "0";
        }

        // Array to map values to their corresponding hexadecimal characters
        char[] hexChars = "0123456789abcdef".toCharArray();
        StringBuilder hexString = new StringBuilder();

        // Process the number for 8 hexadecimal digits (32 bits / 4 bits per hex digit)
        for (int i = 0; i < 8 && num != 0; i++) {
            // Extract the last 4 bits of the number
            int hexDigit = num & 0xF;
            // Prepend the corresponding hexadecimal character to the result
            hexString.insert(0, hexChars[hexDigit]);
            // Right shift the number by 4 bits, filling with zeros on the left
            num >>>= 4;
        }

        return hexString.toString();
    }
}
