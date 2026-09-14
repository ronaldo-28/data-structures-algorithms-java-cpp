class Solution {
    private int findAndCopyWord(char[] c, char[] result, int i, int ri){
        while(i<c.length && c[i]==' '){
            i++;
        }
        int end=i;
        while(end<c.length && c[end]!=' '){
            end++;
        }
        if(i==end) return ri;
        ri=findAndCopyWord(c,result,end+1,ri);

        if(ri>0){
            result[ri]=' ';
            ri++;
        }
        for(int j=i;j<end;j++){
            result[ri]=c[j];
            ri++;
        }
        return ri;
    }
    public String reverseWords(String s) {
        // String[] words=s.trim().split("\\s+");
        // int left=0;
        // int right=words.length-1;
        // while(left<right){
        //     String temp=words[left];
        //     words[left]=words[right];
        //     words[right]=temp;
        //     left++;
        //     right--;
        // }
        // String res=String.join(" ",words);
        // return res;

        char[] c=s.toCharArray();
        char[] result=new char[c.length];
        int ri=findAndCopyWord(c,result,0,0);
        return new String(result,0,ri);
    }
}