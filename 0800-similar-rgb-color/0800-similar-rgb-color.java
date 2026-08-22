class Solution {
    
    public String similarRGB(String color) {
	    // Parse each color into its own variable
        int red = Integer.parseInt(color.substring(1, 3), 16);
        int green = Integer.parseInt(color.substring(3, 5), 16);
        int blue = Integer.parseInt(color.substring(5, 7), 16);
        
		// Get closest color matching the required pattern for each of R,G and B
        int redClosest = closest(red);
        int greenClosest = closest(green);
        int blueClosest = closest(blue);
        
        // Bit shifting to make room for all colors to fit in the 6-digit pattern.
        // Each hexa digit is 4 bits, then if the bits for RGB are RRRRRRRR, GGGGGGGG and BBBBBBBB
        // We shift RRRRRRRR 16 places to the right to get to RRRRRRRR0000000000000000
        // then we shift GGGGGGGG 8 places to get to GGGGGGGG00000000 and blue is left as-is, 
        // as it's the least significant digits.
        // Then we just add RRRRRRRR0000000000000000 + GGGGGGGG00000000 + BBBBBBBB to get to
        // RRRRRRRRGGGGGGGGBBBBBBBB. 
        // The 2 zeroes at the beginning are to pad the case where the number
        // begins with zeroes, as Integer.toHexString does not offer any padding. We'll get rid of them
        // via substring() if we don't need them.
        StringBuilder hexString = new StringBuilder("00").append(Integer.toHexString((redClosest << 16) + (greenClosest << 8) + blueClosest));
        
        return new StringBuilder("#").append(hexString.substring(hexString.length()-6, hexString.length())).toString();
    }
    
    private int closest (int color) {
        // I will work with the leftmost 4 bits (leftmost hexa digit). 
        // Erase the rightmost 4 bits by leftshifting to the right.
        // You can also think of this as dividing by 16. To me,
        // the bitshifting is easier to visualize.
        int colorLeft = color >> 4;
        int prev = colorLeft-1;
        int curr = colorLeft;
        int next = colorLeft+1;
		// Handle over- and underflow
        if (prev < 0) prev = 15;
        if (next > 15) next = 0;
        
        // We can multiply by 16 here, leftshifting by 4 is the same.
        // I did it this way though because I can visualize moving 4 bits
        // to the left to make way for the 4 bits of the rightmost hexa digit
        // For example for 13, try 00 (prevPrev), 11 (currCurr) and 22 (nextNext).
        // 00 (prevPrev) is unnecessary here, but in cases like
        // F1, we need to try EE, FF and 00
        int prevPrev = (prev << 4) + prev;
        int currCurr = (curr << 4) + curr;
        int nextNext = (next << 4) + next;
        
        // Calculate similarities from color to all candidates
        int prevSim = similarity(color, prevPrev);
        int currSim = similarity(color, currCurr);
        int nextSim = similarity(color, nextNext);
        
        // Return color most similar to input color
        if (prevSim >= currSim && prevSim >= nextSim) {
            return prevPrev;
        } else if (currSim >= nextSim) {
            return currCurr;
        } else {
            return nextNext;
        }
    }
    
    
    private int similarity(int left, int right) {
        return -1*(int)Math.pow(left-right, 2);
    }
}