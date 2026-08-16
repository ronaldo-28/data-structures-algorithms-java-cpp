class Solution {
    public boolean isRationalEqual(String s, String t) {
        // Convert both strings to their decimal representations
        return Math.abs(getDecimalValue(s) - getDecimalValue(t)) < 1e-10;
    }
    
    private double getDecimalValue(String s) {
        // If there's no parenthesis, it's a simple decimal number
        if (s.indexOf('(') < 0) {
            return Double.parseDouble(s);
        }
        
        // Handle numbers with repeating decimals
        int openBracket = s.indexOf('(');
        int closeBracket = s.indexOf(')');
        
        // Extract parts of the number
        String integerAndNonRepeating = s.substring(0, openBracket);
        String repeating = s.substring(openBracket + 1, closeBracket);
        
        // Convert the base number (before the repeating part)
        double result = Double.parseDouble(integerAndNonRepeating);
        
        // Handle the repeating part
        double factor = Math.pow(10, repeating.length());
        double repeatingValue = Double.parseDouble(repeating);
        
        // Calculate decimal places before repeating part
        int decimalPlaces = openBracket - s.indexOf('.') - 1;
        if (decimalPlaces < 0) decimalPlaces = 0;
        
        // Formula for converting repeating decimal to fraction
        double multiplier = Math.pow(10, decimalPlaces);
        result += (repeatingValue / (factor - 1)) / multiplier;
        
        return result;
    }
}