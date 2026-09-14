class Solution {
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        int total = 0;
        for(int x : colsum) total += x;
        if(total != upper + lower) return new ArrayList<>();
        
        List<List<Integer>> arr = new ArrayList<>();
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        arr.add(list1);
        arr.add(list2);

        for(int i=0;i<colsum.length;i++){
            list1.add(0);
            list2.add(0);
        }

        for(int i=0;i<colsum.length;i++){
            if(colsum[i]==2){
                list1.set(i,1);
                list2.set(i,1);
                upper--;
                lower--;
                if(upper<0 || lower<0) return new ArrayList<>();
            }
        }
        for(int i=0;i<colsum.length;i++){
            if(colsum[i]==1){
                if(upper>0){
                    list1.set(i,1);
                    upper--;
                }else if(lower >0){
                    list2.set(i,1);
                    lower--;
            }
            }
        }

        if(upper!=0 || lower!=0) return new ArrayList<>();

        return arr;

    }
}