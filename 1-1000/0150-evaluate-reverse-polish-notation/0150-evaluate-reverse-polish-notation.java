class Solution {
    int i;
  public int evalRPN(String[] tokens) {
    i = tokens.length;
    return evalute(tokens);
  }
  public int evalute(String tokens[]){
    String ss = tokens[--i];
    int k = 0;
    int sign = 1;
    int num = 0;
    char s = ss.charAt(0);
    if(ss.length() == 1){
      if(s == '+')
        return evalute(tokens)+evalute(tokens);
      if(s == '-')
        return -evalute(tokens)+evalute(tokens);
      if(s == '*')
        return evalute(tokens)*evalute(tokens);
      if(s == '/'){
        int seconds = evalute(tokens);
        return evalute(tokens)/seconds;
      }
      return s - '0';
    }else{
      if(s == '-'){
        sign=-1;
        s = ss.charAt(++k);
      }
      while(k < ss.length()){
        s = ss.charAt(k++);
        num = num*10 + s-'0';

      }
      return num*sign;
    }
  }
}