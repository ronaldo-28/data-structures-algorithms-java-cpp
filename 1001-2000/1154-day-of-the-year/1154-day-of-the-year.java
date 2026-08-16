class Solution {
    public int dayOfYear(String date) {
      int arr[] = {0,31,28,31,30,31,30,31,31,30,31,30,31};
       int count=0;int num1=0;int num2=0;int year=0;
       for(int i=0;i<4;i++){
         char ch=date.charAt(i);
         year=year*10+(ch-'0');}
      for(int i=0;i<date.length();i++){
        char ch=date.charAt(i);
        if(ch!='-'&&count==1){
        num1=num1*10+(ch-'0');
        }else if(ch!='-'&&count==2){
            num2=num2*10+(ch-'0');}
        else if(ch=='-'){
          count++;   }
      }int ans=0;
       if(num1 > 2 && ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)){
            ans++;
        }
     for(int i=0;i<num1;i++){
         ans+=arr[i]; } 
         ans+=num2;
  return ans;  }
}