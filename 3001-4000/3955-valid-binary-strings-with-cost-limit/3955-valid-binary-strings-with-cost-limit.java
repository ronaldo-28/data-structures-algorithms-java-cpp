class Solution {
    public List<String> generateValidStrings(int n, int k) {
        char[] arr=new char[n];
        Arrays.fill(arr,'0');
        List<String> list=new ArrayList<>();

        generate(n,k,0,arr,list,0);
        return list;
    }
    public void generate(int n,int k, int ind,char[] arr,List<String> list,int cost){
        if(cost >k)
             return ;
        
        if(ind>=n){
            list.add(new String(arr));
            return ;
        }

        generate(n,k,ind+1,arr,list,cost);

        arr[ind]='1';
        generate(n,k,ind+2,arr,list,cost+ind);

        arr[ind]='0';

    }
}