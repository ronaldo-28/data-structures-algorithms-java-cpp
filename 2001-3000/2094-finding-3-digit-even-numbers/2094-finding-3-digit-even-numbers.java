class Solution {
    int indx=0;
    int[]freq=new int[10];
    int []res=new int[450];
    public void rec(int num,int pos){
        if(pos==3){
            res[indx++]=num;
            return;
        }
        for(int i=0;i<10;i++){
            if(freq[i]==0)continue;
            if(pos==0 && i==0)continue;
            if(pos==2 && i%2!=0)continue;
            freq[i]--;
            rec(num*10+i,pos+1);
            freq[i]++;
        }
        return;
    }
    public int[] findEvenNumbers(int[] digits){
        int n=digits.length;
        for(int i:digits){
            freq[i]++;
        }
        rec(0,0);
        return Arrays.copyOf(res,indx);
    }
}