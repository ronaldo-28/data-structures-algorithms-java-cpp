class Solution {
    private int index;
    
    public boolean parseBoolExpr(String expression) {
        index = 0;
        return evaluate(expression);
    }
    
    private boolean evaluate(String expr) {
        char ch = expr.charAt(index);
        
        if (ch == 't') {
            index++; 
            return true;
        }
        if (ch == 'f') {
            index++; 
            return false;
        }
        if (ch == '!') {
            index++; 
            index++; 
            boolean result = evaluate(expr);
            index++; 
            return !result;
        }

        boolean isAnd = (ch == '&');
        boolean isOr = (ch == '|');

        index++; 
        index++; 
        
        boolean result = isAnd ? true : false;
        
        while (true) {
            boolean subResult = evaluate(expr);
            
            if (isAnd) {
                result = result && subResult;
            } else if (isOr) {
                result = result || subResult;
            }
            
            if (expr.charAt(index) == ',') {
                index++; 
            } else { 
                index++; 
                break;
            }
        }
        
        return result;
    }
    
    // Testing the parser.
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.parseBoolExpr("!(f)")); 
        System.out.println(sol.parseBoolExpr("&(t,f,t)")); 
        System.out.println(sol.parseBoolExpr("|(f,f,t)")); 
        System.out.println(sol.parseBoolExpr("|(&(t,f),!(t))")); 
    }
}