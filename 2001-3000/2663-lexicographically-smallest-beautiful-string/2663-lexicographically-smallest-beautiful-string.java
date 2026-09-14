class Solution {
    public String smallestBeautifulString(String s, int k) {
        int n=s.length();
        char[] arr=s.toCharArray();
        for(int i=n-1;i>=0;i--){
            for(char ch=(char)(arr[i]+1);ch<'a'+k;ch++){
                if(check(arr,i,ch)){
                    arr[i]=ch;
                    for(int j=i+1;j<n;j++){
                        for(char c='a';c<'a'+k;c++){
                            if(check(arr,j,c)){
                                arr[j]=c;
                                break;
                            }
                        }
                    }
                return new String(arr);
                }

            }
        }
        return "";
        
    }
    boolean check(char[] arr,int i,char ch){
        if(i>0 &&arr[i-1]==ch)return false;
        if(i>1 && arr[i-2]==ch)return false;
        return true;
    }
}